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


export const servicebuscarEventosCalendario = async (id_usuario) => {
	try {
		const response = await axios.get(`/api/agenda/load_calendar_events/${id_usuario}`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on servicebuscarEventosCalendario");
	}
};


export const serviceSave = async (data) => {
	try {
		let res = await axios.post('/api/agenda/save', data);
		return res.data;

	} catch (e) {
		return handleApiError(e, "Error on serviceSave");
	}
};

