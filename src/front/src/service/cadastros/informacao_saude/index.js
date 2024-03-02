import axios from "axios";
import {handleApiError} from  "@/service/common/handleApiError"


export const serviceLoad = async (id) => {
	try {
		const response = await axios.get(`/api/tipo_info_saude/load/${id}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};


export const serviceSave = async (data) => {
	try {
		let res = await axios.post('/api/tipo_info_saude/save', data);
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSave");
	}
};


export const serviceList = async () => {
	try {
		const response = await axios.get(`/api/tipo_info_saude/list`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceList");
	}
};

