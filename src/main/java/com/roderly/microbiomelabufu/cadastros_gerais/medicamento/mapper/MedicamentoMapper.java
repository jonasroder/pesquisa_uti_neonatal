package com.roderly.microbiomelabufu.cadastros_gerais.medicamento.mapper;

import com.roderly.microbiomelabufu.cadastros_gerais.fabricante_medicamento.model.FabricanteMedicamento;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.dto.request.MedicamentoListResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.dto.request.MedicamentoRequest;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.dto.response.MedicamentoResponse;
import com.roderly.microbiomelabufu.cadastros_gerais.medicamento.model.Medicamento;

public class MedicamentoMapper {

    public static Medicamento medicamentoRequestToMedicamento(MedicamentoRequest medicamentoRequest) {
        var medicamento = new Medicamento();
        var fabricanteMedicamento = medicamentoRequest.id_fabricante_medicamento() != null ? new FabricanteMedicamento(medicamentoRequest.id_fabricante_medicamento()) : null;

        medicamento.setId_medicamento(medicamentoRequest.id_medicamento());
        medicamento.setNome(medicamentoRequest.nome());
        medicamento.setDosagem(medicamentoRequest.dosagem());
        medicamento.setInstrucoes(medicamentoRequest.instrucoes());
        medicamento.setIs_active(medicamentoRequest.is_active());
        medicamento.setFabricanteMedicamento(fabricanteMedicamento);

        return medicamento;
    }


    public static MedicamentoResponse medicamentoToMedicamentoResponse(Medicamento medicamento) {
        return new MedicamentoResponse(
                medicamento.getId_medicamento(),
                medicamento.getNome(),
                medicamento.getDosagem(),
                medicamento.getFabricanteMedicamento() != null ? medicamento.getFabricanteMedicamento().getId_fabricante_medicamento() : null,
                medicamento.getInstrucoes(),
                medicamento.getIs_active()
        );
    }


    public static MedicamentoListResponse medicamentoToMedicamentoListResponse(Medicamento medicamento) {
        return new MedicamentoListResponse(
                medicamento.getId_medicamento(),
                medicamento.getNome(),
                medicamento.getDosagem(),
                medicamento.getFabricanteMedicamento() != null ? medicamento.getFabricanteMedicamento().getDescricao() : null,
                medicamento.getInstrucoes(),
                medicamento.getIs_active()
        );
    }
}
