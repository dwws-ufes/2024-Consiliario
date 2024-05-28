# Etapa 1: Construção da aplicação Vue.js
FROM node:14 AS build-stage

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo package.json e package-lock.json
COPY package*.json ./

# Instale as dependências do projeto
RUN npm install

# Copie o restante do código da aplicação
COPY . .

# Construa a aplicação para produção
RUN npm run build

# Verifique se o diretório dist foi criado
RUN ls -la /app/dist

# Etapa 2: Configuração do servidor Nginx
FROM nginx:alpine

# Copie os arquivos de build para o diretório de servidor do Nginx
COPY --from=build-stage /app/dist /usr/share/nginx/html

# Exponha a porta 80
EXPOSE 80

# Inicie o Nginx
CMD ["nginx", "-g", "daemon off;"]
