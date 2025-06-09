import axios from "axios";
import {handleApiError} from "@/service/common/handleApiError"

export const serviceLoadUltimosNeonatosCadastrados = async () => {
	try {
		const response = await axios.get(`/api/dashboard/getUltimosNeonatosCadastrados`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoad");
	}
};


export const serviceGetCadastrosPorDia = async () => {
	try {
		const response = await axios.get(`/api/dashboard/getCadastrosPorDia`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceLoadUltimosNeonatosCadastrados");
	}
};

export const serviceGetInfeccoesPorAgente = async () => {
	try {
		const response = await axios.get(`/api/dashboard/getInfeccoesPorAgente`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceGetInfeccoesPorAgente");
	}
};

export const getDistribuicaoPerfis = async () => {
	try {
		const response = await axios.get(`/api/dashboard/perfisResistencia`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on getDistribuicaoPerfis");
	}
};

export const getDistribuicaoMecanismos = async () => {
	try {
		const response = await axios.get(`/api/dashboard/mecanismosResistencia`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on getDistribuicaoMecanismos");
	}
};

export const getResistenciaPorAntimicrobiano = async () => {
	try {
		const response = await axios.get(`/api/dashboard/resistenciaAntimicrobiano`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on getResistenciaPorAntimicrobiano");
	}
};


