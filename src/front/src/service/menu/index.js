import axios from "axios";
import {handleApiError} from  "@/service/common/handleApiError"

export const getMenu = async () => {
	try {
		const response = await axios.get(`/api/menu/load`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on getMenu");
	}
};