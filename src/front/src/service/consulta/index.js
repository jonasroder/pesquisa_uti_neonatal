import axios from "axios";
import {handleApiError} from "@/service/common/handleApiError"


export const serviceLoad = async (id_paciente, id_consulta) => {
	try {
		const response = await axios.get(`/api/consulta/load/${id_paciente}/${id_consulta}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};


export const serviceSave = async (data) => {
	try {
		let res = await axios.post('/api/consulta/save', data);
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSave");
	}
};


export const serviceUpload = async (data) => {
	try {
		let res = await axios.post('/api/consulta/upload', data);
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceUpload");
	}
};


export const serviceDownloadArquivo = async (id_arquivo) => {

	try {
		const response = await axios({
			url         : `/api/consulta/download/${id_arquivo}`,
			method      : 'GET',
			responseType: 'blob',
		});

		const contentDisposition = response.headers['content-disposition'];
		let fileName             = 'download';
		if (contentDisposition) {
			const filenameMatch = contentDisposition.match(/filename="?(.+)"?/);
			if (filenameMatch.length === 2) {
				fileName = filenameMatch[1];
			}
		}

		const url  = window.URL.createObjectURL(new Blob([response.data]));
		const link = document.createElement('a');
		link.href  = url;
		link.setAttribute('download', fileName);
		document.body.appendChild(link);
		link.click();
		document.body.removeChild(link);
		window.URL.revokeObjectURL(url);
	} catch (e) {
		return handleApiError(e, "Error on serviceDownloadArquivo");
	}
};
