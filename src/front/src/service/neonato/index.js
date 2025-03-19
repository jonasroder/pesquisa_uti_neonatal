import axios from "axios";
import {handleApiError} from "@/service/common/handleApiError"
import {setNotification} from "@/plugins/notificationService";

export const serviceSave = async (data) => {
	try {
		let res = await axios.post('/api/neonato/save', data);
		if(res.data.success){
			setNotification(res.data.message, 'success');
		} else {
			setNotification(res.data.message, 'error');
		}
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSave");
	}
};


export const serviceLoad = async (id) => {
	try {
		const response = await axios.get(`/api/neonato/load/${id}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};


export const serviceList = async () => {
	try {
		const response = await axios.get(`/api/neonato/list`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceList");
	}
};


export const serviceSaveFilaDownloadExcel = async (data) => {
	try {
		let res = await axios.post('/api/download/save', data);
		if(res.data.success){
			setNotification(res.data.message, 'success');
		} else {
			setNotification(res.data.message, 'error');
		}
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSave");
	}
};


export const serviceApagarNeonato = async (id) => {
	try {
		const response = await axios.post(`/api/neonato/delete/${id}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};