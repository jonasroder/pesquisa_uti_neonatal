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

export const serviceGetColonizacoesPorAgente = async () => {
	try {
		const response = await axios.get(`/api/dashboard/getColonizacoesPorAgente`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on serviceGetColonizacoesPorAgente");
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

export const getInternacoesEmAberto = async () => {
	try {
		const response = await axios.get(`/api/dashboard/internacoesEmAberto`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on getInternacoesEmAberto");
	}
};

export const getListaInternacoesEmAberto = async () => {
	try {
		const response = await axios.get(`/api/dashboard/listaInternacoesEmAberto`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on getListaInternacoesEmAberto");
	}
};

export const getMetricas = async () => {
	try {
		const response = await axios.get(`/api/dashboard/metricas`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on getMetricas");
	}
};

export const getRankingMicroorganismos = async () => {
	try {
		const response = await axios.get(`/api/dashboard/rankingMicroorganismos`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on getRankingMicroorganismos");
	}
};

export const getDistribuicaoPeso = async () => {
	try {
		const response = await axios.get(`/api/dashboard/distribuicaoPeso`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on getDistribuicaoPeso");
	}
};

export const getPacientesSemEventos = async () => {
	try {
		const response = await axios.get(`/api/dashboard/pacientesSemEventos`);
		return response.data;
	} catch (e) {
		return handleApiError(e, "Error on getPacientesSemEventos");
	}
};


