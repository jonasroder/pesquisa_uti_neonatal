import { setNotification } from "@/plugins/notificationService";

/**
 * Extrai o valor do parâmetro 'id' da URL atual.
 * @returns {number|null} O valor do 'id' como número, ou null se não estiver presente.
 */
export const getIdFromUrl = () => {
	const queryParams = new URLSearchParams(window.location.search);
	const id = queryParams.get('id');
	return id ? Number(id) : null;
};



/**
 * Remove todos os caracteres não numéricos de uma string.
 * @param {string} valor A string da qual remover os caracteres não numéricos.
 * @returns {string} A string resultante apenas com números.
 */
export const limparMascara = (valor) => String(valor).replace(/\D+/g, '');



/**
 * Adiciona parâmetros à URL atual e atualiza a barra de endereços sem recarregar a página.
 * @param {Object} parametros Um objeto contendo os parâmetros e seus valores.
 */
export const adicionarParametrosURL = (parametros) => {
	const baseUrl = `${window.location.protocol}//${window.location.host}${window.location.pathname}`;

	const parametrosString = Object.entries(parametros).reduce((acc, [chave, valor], index) => {
		const prefixo = index === 0 ? '?' : '&';
		return `${acc}${prefixo}${encodeURIComponent(chave)}=${encodeURIComponent(valor)}`;
	}, '');

	const newUrl = baseUrl + parametrosString;
	window.history.pushState({path: newUrl}, '', newUrl);
};



/**
 * Verifica se os campos obrigatórios especificados em um objeto estão presentes e não estão vazios.
 * Aceita um array de objetos, onde cada objeto contém uma chave que é o caminho para o campo no objeto
 * principal e o valor é uma string amigável que será exibida na notificação de campos faltantes.
 *
 * @param {Object} objeto - O objeto que será verificado.
 * @param {Object[]} camposObrigatorios - Um array de objetos com os campos obrigatórios e seus nomes amigáveis.
 * @returns {boolean} Retorna true se todos os campos obrigatórios estiverem presentes e preenchidos, false caso contrário.
 *
 * @example
 * const paciente = { nome: 'John Doe', cpf: '123.456.789-00' };
 * const camposObrigatorios = [{ nome: "Nome Completo" }, { cpf: "CPF" }];
 * const resultado = verificarCamposObrigatorios(paciente, camposObrigatorios);
 * // Retorna true se 'nome' e 'cpf' estiverem presentes e preenchidos no objeto paciente.
 */
export const verificarCamposObrigatorios = (objeto, camposObrigatorios) => {
	let camposFaltantes = [];

	const verificar = (valor, caminhoCompleto, nomeAmigavel) => {
		if (valor === undefined || valor === null || (typeof valor === 'string' && valor.trim() === '')) {
			camposFaltantes.push(nomeAmigavel || caminhoCompleto);
			return;
		}

		if (typeof valor === 'object') {
			if (Array.isArray(valor)) {
				valor.forEach((item, index) => {
					if (typeof item === 'object') {
						Object.keys(item).forEach(subChave => {
							verificar(item[subChave], `${caminhoCompleto}[${index}].${subChave}`, item[subChave]);
						});
					}
				});
			} else {
				Object.keys(valor).forEach(subChave => {
					verificar(valor[subChave], `${caminhoCompleto}.${subChave}`, valor[subChave]);
				});
			}
		}
	};

	camposObrigatorios.forEach(objetoCampo => {
		const campo = Object.keys(objetoCampo)[0];
		const nomeAmigavel = objetoCampo[campo];
		const valorCampo = campo.split('.').reduce((acc, chave) => acc && acc[chave], objeto);
		verificar(valorCampo, campo, nomeAmigavel);
	});

	if (camposFaltantes.length > 0) {
		setNotification(`Campos obrigatórios faltando: ${camposFaltantes.join(', ')}`, "error");
		return false;
	}

	return true;
};

