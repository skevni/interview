CREATE DATABASE homework4 TEMPLATE template0;

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

WITH w AS (
SELECT title, avg(cnt) avg_seance, sum(cnt) number_spectators, sum(summ) total_sum
FROM (
SELECT f.title , s.id seance, count(t.id) cnt,sum(t.price) summ
FROM public.tickets t
JOIN public.seances s ON s.id =t.seance_id
JOIN public.films f ON f.id =s.film_id
GROUP BY f.title, s.id
ORDER BY sum(t.price) DESC) tt
GROUP BY title)
SELECT title, avg_seance, number_spectators, total_sum FROM w
UNION ALL
SELECT 'TOTAL:', sum(avg_seance) avg_seance, sum(number_spectators) number_spectators, sum(total_sum) total_sum
FROM w
;

/*
 число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00
 (сколько посетителей пришло с 9 до 15 часов и т.д.).
*/
SELECT '09:00 до 15:00' seance_time,count(*) count_spectators, COALESCE (SUM(t.price),0) total_sum
FROM public.seances s
JOIN public.tickets t ON t.seance_id = s.id
WHERE start_at::time >= '09:00:00'::time AND start_at::time < '15:00:00'::time
UNION
SELECT '15:00 до 18:00' seance_time,count(*) count_spectators, COALESCE (SUM(t.price),0) total_sum
FROM public.seances s
JOIN public.tickets t ON t.seance_id = s.id
WHERE start_at::time >= '15:00:00'::time AND start_at::time < '18:00:00'::time
UNION
SELECT '18:00 до 21:00' seance_time,count(*) count_spectators, COALESCE (SUM(t.price),0) total_sum
FROM public.seances s
JOIN public.tickets t ON t.seance_id = s.id
WHERE start_at::time >= '18:00:00'::time AND start_at::time < '21:00:00'::time
UNION
SELECT '21:00 до 00:00',count(*) count_spectators, COALESCE (SUM(t.price),0) total_sum
FROM public.seances s
JOIN public.tickets t ON t.seance_id = s.id
WHERE start_at::time >= '21:00:00'::time AND start_at::time < '00:00:00'::time
;