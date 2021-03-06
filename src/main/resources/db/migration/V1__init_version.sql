CREATE TABLE IF NOT EXISTS users
(
    id          bigserial       PRIMARY KEY,
    name        VARCHAR(255)    NOT NULL,
    surname     VARCHAR(255)    NOT NULL,
    email       VARCHAR(255)    NOT NULL,
    avatar_url  VARCHAR(255)    NOT NULL,
    created_at  TIMESTAMP       NOT NULL,
    modified_at TIMESTAMP       NOT NULL
);

INSERT INTO public.users (name, surname, email, avatar_url, created_at, modified_at) VALUES ('tomasz', 'nowak', 'tomasz.nowak@wp.pl', 'https://raw.githubusercontent.com/Ashwinvalento/cartoon-avatar/master/lib/images/male/45.png', '2022-06-14 23:14:35.941610', '2022-06-14 23:14:35.941610');
INSERT INTO public.users (name, surname, email, avatar_url, created_at, modified_at) VALUES ('jan', 'jankowski', 'jan.jankowski@wp.pl', 'https://raw.githubusercontent.com/Ashwinvalento/cartoon-avatar/master/lib/images/male/45.png', '2022-06-14 23:14:18.219015', '2022-06-14 23:14:18.219015');
INSERT INTO public.users (name, surname, email, avatar_url, created_at, modified_at) VALUES ('mateusz', 'lis', 'mateusz.lis@wp.pl', 'https://raw.githubusercontent.com/Ashwinvalento/cartoon-avatar/master/lib/images/male/45.png', '2022-06-14 23:14:52.176524', '2022-06-14 23:14:52.176524');
INSERT INTO public.users (name, surname, email, avatar_url, created_at, modified_at) VALUES ('grzegorz', 'netel', 'grzegorz.netel@wp.pl', 'https://raw.githubusercontent.com/Ashwinvalento/cartoon-avatar/master/lib/images/male/45.png', '2022-06-14 23:15:04.055368', '2022-06-14 23:15:04.055368');
INSERT INTO public.users (name, surname, email, avatar_url, created_at, modified_at) VALUES ('szymon', 'dulik', 'szymon.dulik@wp.pl', 'https://raw.githubusercontent.com/Ashwinvalento/cartoon-avatar/master/lib/images/male/45.png', '2022-06-14 23:15:21.013054', '2022-06-14 23:15:21.013054');
