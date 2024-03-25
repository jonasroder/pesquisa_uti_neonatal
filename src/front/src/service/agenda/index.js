import axios from "axios";
import {handleApiError} from  "@/service/common/handleApiError"


export const servicebuscarUsuariosAgenda= async () => {
	try {
		const response = await axios.get(`/api/agenda/load_users_calendar`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on servicebuscarUsuariosAgenda");
	}
};


export const serviceSave = async (data) => {
	try {
		let res = await axios.post('/api/formulario_pittsburgh/save', data);
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSave");
	}
};

