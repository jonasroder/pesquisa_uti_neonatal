import axios from "axios";
import {handleApiError} from  "@/service/common/handleApiError"


export const serviceLoad = async (id_paciente, id_consulta) => {
	try {
		const response = await axios.get(`/api/consulta/load/${id_paciente}/${id_consulta}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};


export const serviceSave = async (data, tipo) => {
	try {
		let res;
		if(tipo === 'insert') {
			res = await axios.post('/api/consulta/insert', data);
		}

		if (tipo === 'update'){
			res = await axios.put('/api/consulta/update', data);
		}

		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSave");
	}
};

