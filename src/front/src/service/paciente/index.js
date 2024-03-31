import axios from "axios";
import {handleApiError} from  "@/service/common/handleApiError"

export const serviceSave = async (data) => {
	try {
		let res= await axios.post('/api/paciente/save', data);
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSave");
	}
};


export const serviceLoad = async (id) => {
	try {
		const response = await axios.get(`/api/paciente/load/${id}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
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


export const serviceList = async () => {
	try {
		const response = await axios.get(`/api/paciente/list`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceList");
	}
};


export const serviceGetInfoBasica= async (id) => {
	try {
		const response = await axios.get(`/api/paciente/get_info_basica/${id}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceGetInfoBasica");
	}
};