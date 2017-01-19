INSERT INTO public.address(
  id, city, "number", street)
VALUES (2, 'Chisinau', '1', 'Stefan cel Mare');

INSERT INTO public.address(
  id, city, "number", street)
VALUES (1, 'London', '221 B', 'Baker Street');

INSERT INTO public.employee(
  id, firstname, lastname, address_id)
VALUES (1, 'John', 'Smith', null);

INSERT INTO public.employee(
  id, firstname, lastname, address_id)
VALUES (2, 'Sherlock', 'Holmes', 1);

INSERT INTO public.employee(
  id, firstname, lastname, address_id)
VALUES (3, 'Ion', 'Popa', null);

INSERT INTO public.employee(
  id, firstname, lastname, address_id)
VALUES (4, 'Alexandru', 'Lapusneanu', 2);