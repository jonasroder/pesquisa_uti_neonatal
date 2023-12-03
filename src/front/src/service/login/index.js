import axios from "axios";
import { setNotification } from "@/service/notificationService";
import { saveToken } from "@/service/tokenService";

export const serviceAuthenticateUser = async (data) => {
	try {
		const res = await axios.post('/authenticate/login', data);
		saveToken(res.data.token); // Salva o token no armazenamento local
		setNotification(`Você foi Autenticado`, "success");
		return res.data;

	} catch (e) {
		console.error(e);
		setNotification("Ocorreu um erro durante a autenticação." + e, "error");
	}
};
