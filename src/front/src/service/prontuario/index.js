import axios from "axios";
import {handleApiError} from "@/service/common/handleApiError"
import {setNotification} from "@/plugins/notificationService";


export const serviceLoad = async (id) => {
	try {
		const response = await axios.get(`/api/prontuario/load/${id}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};


export const serviceLoadColetaIsolado = async (id) => {
	try {
		const response = await axios.get(`/api/prontuario/load_coletas_prontuario/${id}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};


export const serviceSaveEvento = async (data) => {
	try {
		let res = await axios.post('/api/prontuario/save_evento', data);
		setNotification(res.data.message, 'success');
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSaveEvento");
	}
};


export const serviceSaveIsoladoColeta = async (data) => {
	try {
		let res = await axios.post('/api/prontuario/save_coleta', data);
		setNotification(res.data.message, 'success');
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSaveIsoladoColeta");
	}
};


export const serviceExcluirEvento = async (id) => {
	try {
		let res = await axios.post(`/api/prontuario/excluir_evento/${id}`);
		setNotification(res.data.message, 'success');
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceExcluirEvento");
	}
};
