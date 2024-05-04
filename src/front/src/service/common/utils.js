import { setNotification } from "@/plugins/notificationService";
import axios from "axios";

/**
 * Extrai o valor do parâmetro 'id' da URL atual.
 * @returns {number|null} O valor do 'id' como número, ou null se não estiver presente.
 */
export const getIdFromUrl = (param = 'id') => {
	const queryParams = new URLSearchParams(window.location.search);
	const id = queryParams.get(param);
	return id ? Number(id) : 0;
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
 * Verifica se todos os campos obrigatórios em um objeto ou em cada objeto de um array estão preenchidos.
 *
 * A função aceita um array de verificações, onde cada verificação é um objeto que contém dois campos:
 * 'dados', que pode ser um objeto ou um array de objetos, e 'campos', que é um array de strings representando
 * os nomes das chaves obrigatórias nos objetos de dados.
 *
 * Se qualquer um dos campos obrigatórios estiver vazio ou não estiver presente no objeto de dados, a função
 * registra um erro no console e exibe uma notificação de erro. A função retorna 'true' se todos os campos
 * obrigatórios estiverem preenchidos corretamente em todos os objetos fornecidos, e 'false' caso contrário.
 *
 * @param {Array} verificacoes - Um array de objetos de verificação.
 * @returns {boolean} Retorna 'true' se todos os campos obrigatórios estiverem preenchidos, 'false' caso contrário.
 *
 * @example
 * // Define os dados e os campos obrigatórios para a verificação
 * const verificacoes = [
 *     {dados: arrMedicamentoUsoPaciente, campos: ['id_medicamento', 'dosagem', 'id_frequencia']},
 *     {dados: arrSuplementoUsoPaciente, campos: ['id_suplemento', 'dosagem', 'id_frequencia']}
 *     // Adicione mais verificações conforme necessário
 * ];
 *
 * // Chama a função e armazena o resultado
 * const todosCamposPreenchidos = verificarCamposObrigatorios(verificacoes);
 *
 * // Imprime o resultado no console
 * console.log(todosCamposPreenchidos); // Saída: true ou false
 */
export const verificarCamposObrigatorios = (verificacoes) => {
	let todosCamposPreenchidos = true;

	for (const {dados, campos} of verificacoes) {
		if (Array.isArray(dados)) {
			dados.forEach(objeto => {
				campos.forEach(campo => {
					if (!objeto[campo] || objeto[campo].toString().trim() === '') {
						console.error(`Campo obrigatório não preenchido: ${campo}`);
						todosCamposPreenchidos = false;
					}
				});
			});
		} else {
			campos.forEach(campo => {
				if (!dados[campo] || dados[campo].toString().trim() === '') {
					console.error(`Campo obrigatório não preenchido: ${campo}`);
					todosCamposPreenchidos = false;
				}
			});
		}
	}

	if(!todosCamposPreenchidos){
		setNotification("Preencha os campos obrigatórios", "error");
	}

	return todosCamposPreenchidos;
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



/**
 * Retorna as configurações de máscara de input baseadas no tipo especificado.
 * A função suporta múltiplos tipos de máscaras, como CPF, CNPJ, telefone, entre outros.
 * Cada tipo de máscara é configurado para atender aos padrões brasileiros de formatação.
 *
 * @function getMaskSettings
 * @param {Object} params - Objeto contendo o tipo de máscara desejado.
 * @param {string} params.maskType - O tipo de máscara (ex: "cpf", "cnpj", "telefone").
 * @returns {Object} - Um objeto contendo a máscara e configurações adicionais para o tipo especificado.
 */
export const getMaskSettings = ({ maskType }) => {
	const masks = {
		cpf: { mask: '###.###.###-##', eager: true },
		cnpj: { mask: '##.###.###/####-##', eager: true },
		telefone: { mask: '(##) ####-####', eager: false },
		celular: { mask: '(##) #####-####', eager: false },
		cep: { mask: '#####-###', eager: true }
	};

	const defaultMask = { mask: '', eager: false };

	return masks[maskType] || defaultMask;
};



/**
 * Retorna as dimensões da tela do dispositivo do usuário.
 * Esta função é útil para ajustes de layout responsivo ou para lógicas que dependam do tamanho da tela.
 *
 * @function getScreenSize
 * @returns {Object} - Um objeto contendo as propriedades `width` e `height` que representam, respectivamente, a largura e a altura da tela do dispositivo em pixels.
 */
export const getScreenSize = () => {
	const width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
	const height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;

	return { width, height };
};



/**
 * Função genérica para baixar arquivos do servidor.
 * @param {Blob} blob O blob de dados do arquivo a ser baixado.
 * @param {string} filename Nome do arquivo a ser usado no download.
 */
export const downloadFile = (blob, filename) => {
	const url = window.URL.createObjectURL(new Blob([blob]));
	const link = document.createElement('a');
	link.href = url;
	link.setAttribute('download', filename);
	document.body.appendChild(link);
	link.click();

	// Limpeza após o download
	link.parentNode.removeChild(link);
	window.URL.revokeObjectURL(url);
};
