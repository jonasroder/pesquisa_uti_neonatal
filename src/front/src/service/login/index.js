import axios from "axios";

export const serviceAuthenticateUser = async (data) => {
	try {
		const res = await axios.post('/api/authenticate/user', data);
		return res.data; // Retorna os dados diretamente
	} catch (e) {
		console.error(e);
	}
};
