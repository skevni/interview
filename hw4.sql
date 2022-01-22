CREATE DATABASE homework4 TEMPLATE template0;

DROP TABLE public.tickets;
DROP TABLE public.seances;
DROP TABLE public.films;

CREATE TABLE films(id serial8 PRIMARY KEY,
title varchar(255) NOT null,
duration int NOT NULL
);

INSERT INTO public.films
(title, duration)
values('Matrix', 60),('Hobbit', 90),('Harry Potter',120), ('Time', 90), ('Alien', 120);

CREATE TABLE seances
(id serial8 PRIMARY KEY,
film_id bigint REFERENCES films(id),
start_at timestamp,
end_at timestamp);

INSERT INTO public.seances
(film_id, start_at, end_at)
VALUES (1, '2022-01-01 12:20:00', '2022-01-01 13:20:00'), (2, '2022-01-01 12:40:00', '2022-01-01 14:10:00'),
(3, '2022-01-01 08:00:00', '2022-01-01 10:00:00'),(4, '2022-01-01 08:15:00', '2022-01-01 09:45:00'),
(5, '2022-01-01 16:45:00', '2022-01-01 18:45:00');


CREATE TABLE tickets(id serial8 PRIMARY KEY,
ticket_number varchar(15) NOT null,
seance_id bigint REFERENCES seances(id),
price numeric(8,2)
);
INSERT INTO public.tickets
(ticket_number, seance_id, price)
VALUES('1-1', 1, 100.05),('3-1', 3, 110.16);

SELECT * FROM public.tickets t ;

/*
 ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
 Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
 */

SELECT f.title фильм1, s.start_at "время начала", f.duration длительность, f2.title фильм2, s2.start_at "время начала",f2.duration длительность
FROM public.seances s
JOIN public.films f ON f.id = s.film_id
JOIN public.seances s2 ON s2.start_at BETWEEN s.start_at AND s.end_at AND s2.id <> s.id
JOIN public.films f2 ON f2.id = s2.film_id
WHERE EXISTS (SELECT 1 FROM public.seances s2 WHERE s.start_at < s2.end_at AND s.end_at>s2.start_at AND s.id<>s2.id)
ORDER BY s.start_at ;

--Еще один вариант

SELECT f.title фильм1, s.start_at "время начала", f.duration длительность, sf.title фильм2, sf.start_at "время начала", sf.duration длительность
FROM public.seances s
JOIN public.films f ON f.id = s.film_id
JOIN LATERAL (SELECT f2.title, s4.start_at, f2.duration FROM public.seances s4
	JOIN public.films f2 ON f2.id = s4.film_id
	WHERE s.start_at < s4.end_at AND s.end_at>s4.start_at AND s.id<>s4.id
) sf ON TRUE
WHERE EXISTS (SELECT 1 FROM public.seances s2 WHERE s.start_at < s2.end_at AND s.end_at>s2.start_at AND s.id<>s2.id)
ORDER BY s.start_at ;

/*
 перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
 Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
 */

-- EXTRACT (epoch FROM sub.start_at - s.start_at)/60 break_minute
SELECT s.id, f.title, s.start_at, s.end_at,f.duration, sub.start_at, (sub.start_at - s.start_at) break
FROM public.seances s
JOIN public.films f ON f.id = s.film_id
JOIN LATERAL (SELECT s2.start_at, f2.duration FROM public.seances s2
	JOIN public.films f2 ON f2.id = s2.film_id
	WHERE (s2.start_at - s.start_at) > '00:30'::INTERVAL AND s.id<> s2.id) sub ON TRUE
ORDER BY (sub.start_at - s.start_at) ;

--можно так (скорость чуть хуже)

SELECT f.title фильм1, s.start_at "время начала", f.duration длительность, f2.title фильм2, s2.start_at "время начала", (s2.start_at - s.start_at) break
FROM public.seances s
JOIN public.films f ON f.id = s.film_id
JOIN public.seances s2 ON (s2.start_at - s.start_at) > '00:30'::INTERVAL AND s2.id <> s.id
JOIN public.films f2 ON f2.id = s2.film_id
ORDER BY s2.start_at - s.start_at;

