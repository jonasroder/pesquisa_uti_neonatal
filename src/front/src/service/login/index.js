import axios from "axios";

export const serviceGetLoginData = async (data) => {
	try {
		const res = await axios.get(`/api/singin/login/${data}`);
		return res.data; // Return the data directly
	} catch (e) {
		console.error(e);
	}
}
