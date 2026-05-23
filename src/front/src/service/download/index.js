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
		const disposition = response.headers['content-disposition'] || '';
		const match = disposition.match(/filename="?([^";]+)"?/);
		const filename = match?.[1]?.trim() || `neonatos_${id}.xlsx`;
		downloadFile(response.data, filename);
	} catch (error) {
		console.error("Erro ao realizar o download", error);
		handleApiError(error, "Erro ao realizar o download");
	}
};
