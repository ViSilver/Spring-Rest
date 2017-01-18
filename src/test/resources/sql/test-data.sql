INSERT INTO public.address(
  id, city, "number", street)
VALUES (1, 'Chisinau', '1', 'Stefan cel Mare');

INSERT INTO public.address(
  id, city, "number", street)
VALUES (2, 'London', '221 B', 'Baker Street');

INSERT INTO public.employee(
  id, firstname, lastname, address_id)
VALUES (1, 'Ion', 'Popa', null);

INSERT INTO public.employee(
  id, firstname, lastname, address_id)
VALUES (2, 'Alexandru', 'Lapusneanu', 1);


