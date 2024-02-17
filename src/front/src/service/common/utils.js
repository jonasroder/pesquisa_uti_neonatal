import { setNotification } from "@/plugins/notificationService";
import axios from "axios";

/**
 * Extrai o valor do parâmetro 'id' da URL atual.
 * @returns {number|null} O valor do 'id' como número, ou null se não estiver presente.
 */
export const getIdFromUrl = (param = 'id') => {
	const queryParams = new URLSearchParams(window.location.search);
	const id = queryParams.get(param);
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



/**
 * Formata números de telefone com base no número de dígitos.
 * Assume-se que um número com 11 dígitos seja um celular com código de área e nono dígito,
 * e um número com 10 dígitos seja um telefone fixo com código de área.
 *
 * @param {string} numero - O número de telefone a ser formatado.
 * @returns {string} - O número de telefone formatado.
 */
export const formatarTelefone = (numero) => {
	if(!numero) return;

	// Removendo caracteres não numéricos para garantir que o número esteja limpo
	numero = numero.replace(/\D/g, '');

	// Formata como número de celular se tiver 11 dígitos
	if (numero.length === 11) {
		return `(${numero.substring(0, 2)}) ${numero.substring(2, 7)}-${numero.substring(7)}`;
	}
	// Formata como telefone fixo se tiver 10 dígitos
	else if (numero.length === 10) {
		return `(${numero.substring(0, 2)}) ${numero.substring(2, 6)}-${numero.substring(6)}`;
	}
	// Retorna o número sem formatação se não tiver um formato conhecido
	else {
		return numero;
	}
};



/**
 * Busca opções de autocomplete a partir de uma fonte de dados e atualiza a lista de itens.
 * A função é chamada quando as propriedades necessárias são fornecidas.
 * A busca é condicionada pelo estado de ativação (is_active) e uma cláusula WHERE opcional.
 *
 * @async
 * @function getOptionsAutocomplete
 * @param {Object} params - Um objeto contendo as propriedades idColumn, descColumn, tableName, is_active e whereClause.
 * @param {string} params.idColumn - O nome da coluna de identificação na tabela de dados.
 * @param {string} params.descColumn - O nome da coluna de descrição na tabela de dados.
 * @param {string} params.tableName - O nome da tabela de onde os dados serão buscados.
 * @param {boolean} [params.is_active=true] - O estado de ativação para filtrar os dados.
 * @param {string} [params.whereClause=""] - Uma cláusula WHERE adicional para a busca SQL.
 * @returns {Promise<ref<Array>>} - Uma ref de Vue com um array de objetos contendo as opções para o autocomplete.
 */
export const getOptionsAutocomplete = async ({ idColumn, descColumn, tableName, is_active = true, whereClause = "" }) => {
	try {
		const data = {
			idColumn,
			descColumn,
			tableName,
			is_active,
			whereClause
		};

		const res = await axios.post('/api/dictionary/autocomplete', data);

		if (Array.isArray(res.data)) {
			return res.data.map((item => ({value: item.value, label: item.label})));
		}

		return [];

	} catch (error) {
		setNotification("Ocorreu um erro ao buscar as options de " + tableName, "error");
		console.error("Erro ao buscar opções de autocomplete:", error);
		return [];
	}
};
