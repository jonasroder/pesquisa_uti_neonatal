<script setup>
import { serviceAuthenticateUser } from "@/service/login";
import { onMounted, onUnmounted, reactive, ref } from "vue";
import { useRouter } from "vue-router";
import logoLogin from '../../assets/logo_login_uti.png';

const router       = useRouter();
const isLoading    = ref(false);
const showPassword = ref(false);

const login = reactive({
    usuario   : "",
    senha     : "",
    rememberMe: false,
});

const submitLogin = async () => {
    isLoading.value = true;
    const response = await serviceAuthenticateUser(login);
    if (response) {
        router.push({ name: 'Dashboard-Home' });
    }
    isLoading.value = false;
};

const handleEnter = (event) => {
    if (event.key === 'Enter' && login.usuario !== "" && login.senha !== "") {
        submitLogin();
    }
};

onMounted(() => window.addEventListener('keydown', handleEnter));
onUnmounted(() => window.removeEventListener('keydown', handleEnter));
</script>

<template>
    <div class="login-layout">

        <!-- Painel esquerdo: marca -->
        <div class="login-brand">
            <div class="login-brand__shapes" aria-hidden="true">
                <span class="shape shape--a" />
                <span class="shape shape--b" />
                <span class="shape shape--c" />
            </div>
            <div class="login-brand__content">
                <div class="login-brand__logo-wrap">
                    <img :src="logoLogin" alt="Logo" class="login-brand__logo" />
                </div>
                <div class="login-brand__text">
                    <h2 class="login-brand__title">Portal de Pesquisa</h2>
                    <p class="login-brand__subtitle">Epidemiologia em UTI Neonatal</p>
                </div>
                <div class="login-brand__divider" />
                <p class="login-brand__desc">
                    Plataforma de coleta e análise de dados clínicos para pesquisadores autorizados.
                </p>
                <div class="login-brand__badges">
                    <span class="badge">
                        <i class="fa-solid fa-shield-halved" />
                        Acesso seguro
                    </span>
                    <span class="badge">
                        <i class="fa-solid fa-database" />
                        Dados protegidos
                    </span>
                </div>
            </div>
        </div>

        <!-- Painel direito: formulário -->
        <div class="login-form-panel">
            <div class="login-form">

                <div class="login-form__logo-mobile">
                    <img :src="logoLogin" alt="Logo" class="login-form__logo-img" />
                </div>

                <div class="login-form__header">
                    <h1>Entrar</h1>
                    <p>Acesse com suas credenciais institucionais</p>
                </div>

                <div class="login-form__fields">
                    <v-text-field
                        v-model="login.usuario"
                        label="E-mail institucional"
                        type="email"
                        prepend-inner-icon="fa-solid fa-envelope"
                        variant="outlined"
                        density="comfortable"
                        color="primary"
                        hide-details="auto"
                        autocomplete="username"
                    />

                    <v-text-field
                        v-model="login.senha"
                        label="Senha"
                        :type="showPassword ? 'text' : 'password'"
                        prepend-inner-icon="fa-solid fa-lock"
                        :append-inner-icon="showPassword ? 'fa-solid fa-eye-slash' : 'fa-solid fa-eye'"
                        @click:append-inner="showPassword = !showPassword"
                        variant="outlined"
                        density="comfortable"
                        color="primary"
                        hide-details="auto"
                        autocomplete="current-password"
                    />
                </div>

                <v-checkbox
                    v-model="login.rememberMe"
                    label="Manter conectado por 30 dias"
                    color="primary"
                    density="compact"
                    hide-details
                    class="login-form__remember"
                />

                <v-btn
                    color="primary"
                    block
                    size="large"
                    :loading="isLoading"
                    :disabled="!login.usuario || !login.senha"
                    @click="submitLogin"
                    elevation="0"
                    class="login-btn"
                >
                    Entrar
                </v-btn>

                <p class="login-form__footer">
                    Sistema de uso exclusivo para pesquisadores autorizados
                </p>
            </div>
        </div>

    </div>
</template>

<style lang="scss" scoped>
// ── Layout ───────────────────────────────────────────────
.login-layout {
    position: fixed;
    inset: 0;
    display: flex;
    overflow: hidden;
}

// ── Painel da marca ──────────────────────────────────────
.login-brand {
    position: relative;
    flex: 0 0 40%;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
    background: linear-gradient(160deg, #071e36 0%, #0d3a5c 60%, #155e8a 100%);

    &__shapes {
        position: absolute;
        inset: 0;
        pointer-events: none;
    }

    &__content {
        position: relative;
        z-index: 2;
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
        padding: 48px 44px;
        gap: 0;
    }

    &__logo-wrap {
        width: 156px;
        height: 156px;
        border-radius: 32px;
        background: #ffffff;
        box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
        display: flex;
        align-items: center;
        justify-content: center;
        margin-bottom: 24px;
    }

    &__logo {
        width: 115px;
        height: 115px;
        object-fit: contain;
    }

    &__text {
        margin-bottom: 20px;
    }

    &__title {
        font-size: 1.4rem;
        font-weight: 700;
        color: #ffffff;
        line-height: 1.3;
        margin: 0 0 6px;
    }

    &__subtitle {
        font-size: 0.75rem;
        font-weight: 500;
        color: rgba(255, 255, 255, 0.5);
        text-transform: uppercase;
        letter-spacing: 1.4px;
        margin: 0;
    }

    &__divider {
        width: 32px;
        height: 1px;
        background: rgba(255, 255, 255, 0.15);
        border-radius: 1px;
        margin-bottom: 20px;
    }

    &__desc {
        font-size: 0.82rem;
        color: rgba(255, 255, 255, 0.45);
        line-height: 1.7;
        max-width: 240px;
        margin: 0 0 24px;
    }

    &__badges {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: center;
        gap: 8px;
    }
}

.badge {
    display: inline-flex;
    align-items: center;
    gap: 6px;
    padding: 5px 12px;
    background: rgba(255, 255, 255, 0.06);
    border: 1px solid rgba(255, 255, 255, 0.1);
    border-radius: 20px;
    font-size: 0.72rem;
    color: rgba(255, 255, 255, 0.5);
    letter-spacing: 0.2px;
    white-space: nowrap;
}

.shape {
    position: absolute;
    border-radius: 50%;
    background: rgba(255, 255, 255, 0.025);
    border: 1px solid rgba(255, 255, 255, 0.05);

    &--a { width: 360px; height: 360px; top: -120px; right: -120px; }
    &--b { width: 200px; height: 200px; bottom: 10px;  left:  -70px; }
    &--c { width:  90px; height:  90px; bottom: 160px; right:  40px; }
}

// ── Painel do formulário ─────────────────────────────────
.login-form-panel {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    background: #ffffff;
    padding: 48px 40px;
    overflow-y: auto;
}

.login-form {
    width: 100%;
    max-width: 360px;

    &__logo-mobile {
        display: none;
        margin-bottom: 24px;
    }

    &__logo-img {
        height: 44px;
        width: auto;
        object-fit: contain;
    }

    &__header {
        margin-bottom: 28px;

        h1 {
            font-size: 1.75rem;
            font-weight: 700;
            color: #0d2137;
            line-height: 1.2;
            margin: 0 0 6px;
        }

        p {
            font-size: 0.855rem;
            color: #64748B;
            margin: 0;
        }
    }

    &__fields {
        display: flex;
        flex-direction: column;
        gap: 14px;
        margin-bottom: 8px;
    }

    &__remember {
        margin-bottom: 20px;

        :deep(.v-label) {
            font-size: 0.83rem;
            color: #64748B;
        }
    }

    &__footer {
        margin-top: 24px;
        font-size: 0.72rem;
        color: #B0BEC5;
        text-align: center;
        line-height: 1.5;
    }
}

.login-btn {
    font-weight: 600;
    font-size: 0.92rem;
    letter-spacing: 0.2px;
    border-radius: 10px !important;
    height: 48px !important;
    text-transform: none !important;
}

// ── Responsivo ───────────────────────────────────────────
@media (max-width: 860px) {
    .login-brand {
        flex: 0 0 36%;

        &__desc   { display: none; }
        &__badges { display: none; }

        &__content { padding: 40px 32px; }
    }
}

@media (max-width: 600px) {
    .login-layout {
        flex-direction: column;
        overflow-x: hidden;
        overflow-y: auto;
        position: static;
        width: 100%;
        min-height: 100dvh;
    }

    .login-brand {
        flex: 0 0 auto;

        &__content   { padding: 20px 24px; gap: 4px; }
        &__logo-wrap { display: none; }
        &__text      { margin-bottom: 0; }
        &__title     { font-size: 1.05rem; }
        &__subtitle  { font-size: 0.7rem; }
        &__divider   { display: none; }
        &__desc      { display: none; }
        &__badges    { display: none; }
    }

    .shape--a { width: 160px; height: 160px; top: -50px; right: -50px; }
    .shape--b, .shape--c { display: none; }

    .login-form-panel {
        flex: 1;
        align-items: flex-start;
        padding: 32px 24px 48px;
    }

    .login-form {
        max-width: 100%;

        &__logo-mobile {
            display: flex;
            justify-content: center;
            margin-bottom: 24px;
        }

        &__logo-img {
            height: 80px;
            width: auto;
        }

        &__header {
            text-align: center;
            margin-bottom: 24px;

            h1 { font-size: 1.5rem; }
        }
    }
}
</style>
