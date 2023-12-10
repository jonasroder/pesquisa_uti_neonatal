export const getIdFromUrl = () => {
	const queryParams = new URLSearchParams(window.location.search);
	const id = queryParams.get('id');
	return id ? Number(id) : null;
};
