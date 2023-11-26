<template>
    <div class="hello">
        <div class="texto">
            <b>Denise, Te amamarei para sempre!</b>
        </div>
        <img src="@/assets/jonasedenise.jpg" alt="Foto" class="foto-surpresa">
    </div>
</template>

<style>
.hello {
    text-align: center; /* Centraliza o conteúdo */
    padding: 20px;
    background-color: #f0f0f0; /* Cor de fundo */
    border-radius: 15px; /* Cantos arredondados */
    margin: 20px auto; /* Centraliza o bloco na página */
    max-width: 600px; /* Largura máxima do bloco */
}

.texto {
    margin-bottom: 20px; /* Espaço entre o texto e a imagem */
}

.texto b {
    font-size: 24px; /* Tamanho da fonte */
    color: #333; /* Cor da fonte */
}

.foto-surpresa {
    max-width: 100%; /* Garante que a imagem não ultrapasse a largura do contêiner */
    height: auto; /* Mantém a proporção da imagem */
    border-radius: 10px; /* Cantos arredondados na imagem */
}
</style>
