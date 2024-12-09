import axios from "axios";
import {handleApiError} from  "@/service/common/handleApiError"
import {setNotification} from "@/plugins/notificationService";


export const serviceLoad = async (idUsuario) => {
	try {
		const response = await axios.get(`/api/usuario/load/${idUsuario}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};


export const serviceSave = async (data) => {
	try {
		let res = await axios.post('/api/usuario/save', data);

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


export const serviceList = async () => {
	try {
		const response = await axios.get(`/api/usuario/list`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceList");
	}
};

