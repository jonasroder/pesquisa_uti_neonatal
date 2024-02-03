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
export const limparMascara = (valor) => valor.replace(/\D+/g, '');



/**
 * Adiciona parâmetros à URL atual e atualiza a barra de endereços sem recarregar a página.
 * @param {Object} parametros Um objeto contendo os parâmetros e seus valores.
 */
export const adicionarParametrosURL = (parametros) => {
	// Constrói a base da URL.
	const baseUrl = `${window.location.protocol}//${window.location.host}${window.location.pathname}`;

	// Converte os parâmetros em uma string de consulta, iniciando com '?'
	const parametrosString = Object.entries(parametros).reduce((acc, [chave, valor], index) => {
		const prefixo = index === 0 ? '?' : '&';
		return `${acc}${prefixo}${encodeURIComponent(chave)}=${encodeURIComponent(valor)}`;
	}, '');

	// Cria a nova URL com os parâmetros.
	const newUrl = baseUrl + parametrosString;

	// Atualiza a barra de endereços e o histórico do navegador sem recarregar a página.
	window.history.pushState({path: newUrl}, '', newUrl);
};

