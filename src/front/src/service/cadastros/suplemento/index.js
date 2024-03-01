import axios from "axios";
import {handleApiError} from  "@/service/common/handleApiError"


export const serviceLoad = async (id_suplemento) => {
	try {
		const response = await axios.get(`/api/suplemento/load/${id_suplemento}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};


export const serviceSave = async (data) => {
	try {
		let res = await axios.post('/api/suplemento/save', data);
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSave");
	}
};


export const serviceList = async () => {
	try {
		const response = await axios.get(`/api/suplemento/list`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceList");
	}
};

