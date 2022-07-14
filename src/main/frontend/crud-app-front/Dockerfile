### STAGE 1: Build ###
FROM node:latest AS build
WORKDIR /usr/src/app
COPY src/main/frontend/crud-app-front/package.json src/main/frontend/crud-app-front/package-lock.json ./
RUN npm install
COPY ./src/main/frontend/crud-app-front .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:latest
COPY src/main/frontend/crud-app-front/nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/crud-app-front /usr/share/nginx/html
