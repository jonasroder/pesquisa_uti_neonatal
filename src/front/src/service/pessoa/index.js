import axios from "axios";
import { setNotification } from "@/service/notificationService";

export const serviceSave = async (data, tipo) => {
	try {
		let res;

		if(tipo == 'insert') {
			res = await axios.post('/api/pessoa/insert', data);
		}

		if (tipo == 'update'){
			res = await axios.put('/api/pessoa/update', data);
		}

		return res.data;

	} catch (e) {
		console.error(e);
		setNotification("Error on serviceSavePessoa: " + e, "error");
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