import axios from "axios";
import { setNotification } from "@/service/notificationService";
import { saveToken, setAuthHeader } from "@/service/tokenService";

export const serviceAuthenticateUser = async (data) => {
	try {
		const res = await axios.post('/api/authenticate/login', data);
		saveToken(res.data.token);
		setAuthHeader();
		setNotification(`Você foi Autenticado`, "success");
		return res.data;

	} catch (e) {
		console.error(e);
		setNotification("Ocorreu um erro durante a autenticação." + e, "error");
	}
};
