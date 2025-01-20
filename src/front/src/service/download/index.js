import axios from "axios";
import {handleApiError} from "@/service/common/handleApiError"
import {downloadFile} from "@/service/common/utils";



export const serviceList = async () => {
	try {
		const response = await axios.get(`/api/download/list`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceList");
	}
};


export const serviceDownloadDataExcel = async (id) => {
	try {
		const response = await axios.get(`/api/download/${id}/file`, {
			responseType: 'blob'
		});
		downloadFile(response.data, `neonatos_${id}.xlsx`);
	} catch (error) {
		console.error("Erro ao realizar o download", error);
		handleApiError(error, "Erro ao realizar o download");
	}
};
