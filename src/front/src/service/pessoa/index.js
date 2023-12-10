import axios from "axios";
import {handleApiError} from  "@/service/common/handleApiError"

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
		debugger
		return handleApiError(e, "Error on serviceSave");
	}
};


export const getEnderecoByCep = async (cep) => {
	try {
		const response = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceSave");
	}
};