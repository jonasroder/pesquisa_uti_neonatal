import axios from "axios";
import {handleApiError} from  "@/service/common/handleApiError"
import {setNotification} from "@/plugins/notificationService";


export const serviceLoad = async (id) => {
	try {
		const response = await axios.get(`/api/sitio_malformacao/load/${id}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};


export const serviceSave = async (data) => {
	try {
		let res = await axios.post('/api/sitio_malformacao/save', data);
		setNotification(res.data.message, 'success');
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSave");
	}
};


export const serviceList = async () => {
	try {
		const response = await axios.get(`/api/sitio_malformacao/list`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceList");
	}
};

