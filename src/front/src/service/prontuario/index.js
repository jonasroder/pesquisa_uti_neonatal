import axios from "axios";
import {handleApiError} from "@/service/common/handleApiError"
//import {setNotification} from "@/plugins/notificationService";
//import {downloadFile} from "@/service/common/utils";


export const serviceLoad = async (id) => {
	try {
		const response = await axios.get(`/api/prontuario/load/${id}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};
