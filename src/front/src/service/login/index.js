import axios from "axios";
import { setNotification } from "@/plugins/notificationService";
import { saveToken, removeToken } from "@/service/common/tokenService";

export const serviceAuthenticateUser = async (data) => {
	try {
		await removeToken()
		const res = await axios.post('/authenticate/login', data);
		saveToken(res.data.token);
		return res.data;

	} catch (e) {
		console.error(e);
		setNotification("Ocorreu um erro durante a autenticação." + e, "error");
	}
};
