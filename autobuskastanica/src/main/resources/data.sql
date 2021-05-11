insert into prevoznik (id, naziv, adresa, PIB) values (1, 'Lasta', 'Beograd', 103376522);
insert into prevoznik (id, naziv, adresa, PIB) values (2, 'Vojvodina', 'Novi Sad', 123564849);
insert into prevoznik (id, naziv, adresa, PIB) values (3, 'Rumatrans', 'Ruma', 123546888);

insert into linija (id, brroj_mesta, cena_karte, vreme_polska, destinacija, prevoznik_id) values (1, 35, 350.0, '2020-12-12','Bajina Basta', 1);
insert into linija (id, brroj_mesta, cena_karte, vreme_polska, destinacija, prevoznik_id) values (2, 40, 550.0, '2020-11-12','Nis', 2);
insert into linija (id, brroj_mesta, cena_karte, vreme_polska, destinacija, prevoznik_id) values (3, 28, 380.0, '2020-11-12','Svrljig', 3);

INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

