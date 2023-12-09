import axios from "axios";
import { setNotification } from "@/service/notificationService";

export const serviceAuthenticateTeste = async () => {
	try {
		const res = await axios.post('/api/pessoa/teste');
		return res.data;

	} catch (e) {
		console.error(e);
		setNotification("Ocorreu um erro durante a autenticação." + e, "error");
	}
};


export const getEnderecoByCep = async (cep) => {
	try {
		const response = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);
		return response.data;
	} catch (error) {
		console.error(error);
		throw new Error('Ocorreu um erro ao buscar o endereço.');
	}
};