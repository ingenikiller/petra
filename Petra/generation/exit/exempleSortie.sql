/**************************************************************************************************
	Mantis 3714: integ ADTR


Création le: 07/11/2014 à 11:09
Auteur: OJE

Version PeTRA:1.3.3
****************************************************************************************************/

update ut_uti set eshs='ES' where codsoc=1 and uti='GNC';
--commentaire
----------------------------------------------------------------------------------
--Extraction UPRC
--nom:DEGTAR
--uprc test
----------------------------------------------------------------------------------
-- table UT_PRC
DELETE FROM UT_PRC WHERE CODSOC=~societe AND NOMPRC='DEGTAR';
INSERT INTO UT_PRC(NOMPRC, ST1, DATDMD, HEUDMD, LIBPRC, LIRPRC, AUTOMANU, ESHS, BATCH, PERIODE, ST2, UTI, NBEDI, DATMOD, UTIMOD, CODSOC, NOMCAL, ATT_DATFIX, DATFIX, DATREFDMD, HEUREFDMD, TZ) VALUES('DEGTAR', 4, '20131115', '1100', 'Calcul Deg.Tar Permanent et Opération', 'Calcul Deg.Tar', 'M', 'ES', 'CADENCIER', 'CAL', 'Erreur édition GCADA Order 4', 'GNC', 13, to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, 'DEGTAR', 'N', ' ', ' ', ' ', ' ');
--
SELECT count(1) FROM UT_PRC WHERE CODSOC=~societe AND NOMPRC='DEGTAR';

--suppression des ut_par existant de toute l'uprc
--suppression des ut_spl fils
DELETE FROM ut_par WHERE codsoc=~societe AND numero IN (SELECT numero FROM ut_spl WHERE ut_par.codsoc=ut_spl.codsoc AND nomprc='DEGTAR');
DELETE FROM ut_spl WHERE codsoc=~societe AND numpere<>0 AND nomprc='DEGTAR';

DELETE FROM UT_SPL WHERE codsoc=~societe AND NOMPRC='DEGTAR' AND numpere=0;
--element de procedure numero 2
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('MAJ-BOX', 'GNC', '20120207', '1710', '20140422', '1033', '20140422', '1033', 0, 4, ' ', 'pdf', 'CADENCIER', 0, ' ', 'UEDI', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 0, '#trt@ap$spl:', 'MAJ pour box abaco', 'E', 'N', ' ', 'DEGTAR', 2, 'O', 'STD', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:vide.eta', 0, ' ', 0, 0, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 2
--

--element de procedure numero 3
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('GEDTE', 'GNC', '20120207', '1711', '20140422', '1033', '20140422', '1033', 0, 4, ' ', 'pdf', 'GNCFIL', 0, ' ', 'GEDT', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 1, '#trt@ap$spl:', 'Génération des événements A/RCT', 'E', 'N', ' ', 'DEGTAR', 3, 'N', ' ', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:genarct.maq', 0, ' ', 490, 0, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 3
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=3 AND numpere=0), 0, 'E', 'NSMULTI_ENT           multi', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=3 AND numpere=0), 1, 'E', 'NV                                        15                 0', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'codsoc');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=3 AND numpere=0), 2, 'E', 'VUwv_rcts', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=3 AND numpere=0), 3, 'E', 'TR                                        ASC', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'codsoc');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=3 AND numpere=0), 4, 'E', 'TR                                        ASC', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'lib1');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=3 AND numpere=0), 5, 'E', 'PLRCTS', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
--

--element de procedure numero 4
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('GCADA', 'GNC', '20120207', '1714', '20140422', '1033', '20140422', '1033', 38, 4, ' ', 'pdf', 'GNCFIL', 0, ' ', 'GCAD', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 1, '#trt@ap$spl:', 'Extraction prix AG/OS pour valorisation', 'E', 'N', ' ', 'DEGTAR', 4, 'N', ' ', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:cession_ope.maq', 0, ' ', 490, 76, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 4
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=4 AND numpere=0), 0, 'E', 'NSMULTI_ENT           multi', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=4 AND numpere=0), 1, 'E', 'NV                                        15                 0', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'codsoc');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=4 AND numpere=0), 2, 'E', 'VUwv_cession_ope', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=4 AND numpere=0), 3, 'E', 'PLCALCOS', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
--

--element de procedure numero 5
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('DEGTARE', 'GNC', '20070604', '1724', '20140422', '1033', '20140422', '1033', 0, 4, ' ', 'pdf', 'CADENCIER', 0, ' ', 'DEGTAR', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 1, '#trt@ap$spl:', '1Dég.Tar Perm + Opération (tar,LIG)', 'E', 'N', ' ', 'DEGTAR', 5, 'N', ' ', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:deg_tarif_LIG.maq', 0, ' ', 490, 1, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 5
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=5 AND numpere=0), 0, 'E', 'NSMULTI_ENT           multi', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=5 AND numpere=0), 1, 'E', 'NV                                        15                 0', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'codsoc');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=5 AND numpere=0), 5, 'E', 'VUwv_deg_tarif', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=5 AND numpere=0), 6, 'E', 'PLDEGTAR', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
--

--element de procedure numero 6
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('DEGTARE', 'GNC', '20070604', '1726', '20140422', '1033', '20140422', '1033', 0, 4, ' ', 'pdf', 'CADENCIER', 0, ' ', 'DEGTAR', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 1, '#trt@ap$spl:', '2Dég.Tar Perm + Opération (PIE)', 'E', 'N', ' ', 'DEGTAR', 6, 'N', ' ', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:deg_tarif_PIE.maq', 0, ' ', 490, 1, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 6
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=6 AND numpere=0), 0, 'E', 'NSMULTI_ENT           multi', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=6 AND numpere=0), 1, 'E', 'NV                                        15                 0', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'codsoc');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=6 AND numpere=0), 3, 'E', 'VUwv_deg_tarif2', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=6 AND numpere=0), 4, 'E', 'PLDEGTA2', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
--

--element de procedure numero 7
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('DEGTARE', 'GNC', '20070604', '1726', '20140422', '1033', '20140422', '1033', 0, 4, ' ', 'pdf', 'CADENCIER', 0, ' ', 'DEGTAR', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 1, '#trt@ap$spl:', '3Dég.Tar Perm + Opération (RFx)', 'E', 'N', ' ', 'DEGTAR', 7, 'N', ' ', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:deg_tarif_RFx.maq', 0, ' ', 490, 1, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 7
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=7 AND numpere=0), 0, 'E', 'NSMULTI_ENT           multi', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=7 AND numpere=0), 1, 'E', 'NV                                        15                 0', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'codsoc');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=7 AND numpere=0), 3, 'E', 'VUwv_deg_tarif2', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=7 AND numpere=0), 4, 'E', 'PLDEGTA3', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
--

--element de procedure numero 8
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('DEGTARE', 'GNC', '20070604', '1726', '20140422', '1033', '20140422', '1034', 0, 4, ' ', 'pdf', 'CADENCIER', 0, ' ', 'DEGTAR', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 1, '#trt@ap$spl:', '4Dég.Tar Perm + Opération(POR,ECO)', 'E', 'N', ' ', 'DEGTAR', 8, 'N', ' ', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:deg_tarif_POR.maq', 0, ' ', 490, 1, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 8
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=8 AND numpere=0), 0, 'E', 'NSMULTI_ENT           multi', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=8 AND numpere=0), 1, 'E', 'NV                                        15                 0', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'codsoc');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=8 AND numpere=0), 3, 'E', 'VUwv_deg_tarif2', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=8 AND numpere=0), 4, 'E', 'PLDEGTA4', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
--

--element de procedure numero 9
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('DEGTARE', 'GNC', '20070604', '1726', '20140422', '1034', '20140422', '1034', 4, 4, ' ', 'pdf', 'CADENCIER', 0, ' ', 'DEGTAR', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 1, '#trt@ap$spl:', '5Dég.Tar Perm + Opération (VTX)', 'E', 'N', ' ', 'DEGTAR', 9, 'N', ' ', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:deg_tarif_VTX.maq', 0, ' ', 490, 1, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 9
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=9 AND numpere=0), 0, 'E', 'NSMULTI_ENT           multi', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=9 AND numpere=0), 1, 'E', 'NV                                        15                 0', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'codsoc');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=9 AND numpere=0), 3, 'E', 'VUwv_deg_tarif2', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=9 AND numpere=0), 4, 'E', 'PLDEGTA5', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
--

--element de procedure numero 10
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('DEGTANE', 'GNC', '20111128', '1530', '20140422', '1034', '20140422', '1034', 1, 4, ' ', 'pdf', 'CADENCIER', 0, ' ', 'DEGTAN', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 1, '#trt@ap$spl:', '5bisDég.Tar Perm + Opération (VTX N)', 'E', 'N', ' ', 'DEGTAR', 10, 'N', ' ', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:deg_tarifn_VTX.maq', 0, ' ', 490, 1, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 10
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=10 AND numpere=0), 0, 'E', 'NSMULTI_ENT           multi', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=10 AND numpere=0), 1, 'E', 'NV                                        15                 0', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'codsoc');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=10 AND numpere=0), 2, 'E', 'VUwv_deg_tarif2', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=10 AND numpere=0), 3, 'E', 'PLDEGTN3', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
--

--element de procedure numero 11
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('DEGTANE', 'GNC', '20080128', '1133', '20140422', '1034', '20140422', '1034', 0, 4, ' ', 'pdf', 'CADENCIER', 0, ' ', 'DEGTAN', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 1, '#trt@ap$spl:', '6Dég.Tar Perm + Opération (RFx N,A)', 'E', 'N', ' ', 'DEGTAR', 11, 'N', ' ', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:deg_tarifn_RFx.maq', 0, ' ', 490, 1, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 11
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=11 AND numpere=0), 0, 'E', 'NSMULTI_ENT           multi', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=11 AND numpere=0), 1, 'E', 'NV                                        15                 0', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'codsoc');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=11 AND numpere=0), 3, 'E', 'VUwv_deg_tarif2', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=11 AND numpere=0), 4, 'E', 'PLDEGTAN', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
--

--element de procedure numero 12
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('DEGTANE', 'GNC', '20080128', '1133', '20140422', '1034', '20140422', '1034', 0, 4, ' ', 'pdf', 'CADENCIER', 0, ' ', 'DEGTAN', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 1, '#trt@ap$spl:', '7Dég.Tar Perm + Opération(PRS BC,POR N)', 'E', 'N', ' ', 'DEGTAR', 12, 'N', ' ', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:deg_tarifn_PRS.maq', 0, ' ', 490, 1, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 12
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=12 AND numpere=0), 0, 'E', 'NSMULTI_ENT           multi', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=12 AND numpere=0), 1, 'E', 'NV                                        15                 0', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', 'codsoc');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=12 AND numpere=0), 3, 'E', 'VUwv_deg_tarif2', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
INSERT INTO UT_PAR(NUMERO, NUMPAR, AUTOMANU, PARAM, DATMOD, UTIMOD, CODSOC, NOMTABLE, NOMCHP) VALUES((SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR' AND ord=12 AND numpere=0), 4, 'E', 'PLDEGTN2', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, ' ', ' ');
--

--element de procedure numero 13
INSERT INTO UT_SPL(FCT, UTI, DATACT, HEUACT, DATDEB, HEUDEB, DATFIN, HEUFIN, PAGE, ST1, ST2, FPRINT, BATCH, NBI, FORMA, AFCT, AUTI, NUMERO, NBC, DIR, TFC, AUTOMANU, EDIT, MAQ, NOMPRC, ORD, CONTIN, CODE_MAQ, DATMOD, UTIMOD, CODSOC, NOMMAQ, NUMPERE, CODPLED, ETAPE, POSEXT, ESHS, NUMMES, ATTR, INDSUP, VALZN1, VALZN2, VALZN3, VALZN4, VALZN5) VALUES('VER-BOX', 'GNC', '20081117', '0856', '20140422', '1034', '20140422', '1034', 0, 4, 'Erreur édition GCADA Order 4', 'pdf', 'CADENCIER', 0, ' ', 'UEDI', ' ', PKG_COMMUN.f_reserve_cpt(~societe, 'ELTLIST'), 0, '#trt@ap$spl:', 'Contrôle des box', 'E', 'N', ' ', 'DEGTAR', 13, 'O', 'STD', to_char(SYSDATE,'YYYYMMDD'), 'OSI_SQL', ~societe, '#TRT@ap$maq:vide.eta', 0, ' ', 0, 0, 'ES', 0, 0, 0, ' ', ' ', ' ', ' ', ' ');
--generation ut_par pour element 13
--

--requete update des valeurs de codsoc en paramètres
UPDATE ut_par set param='NV                                        '||15||'                 0' WHERE upper(nomchp)='CODSOC' AND codsoc=15 AND numero IN (SELECT numero FROM ut_spl WHERE codsoc=~societe AND nomprc='DEGTAR');
--maj statut UPRC
UPDATE ut_prc SET eshs='HS' WHERE codsoc=~societe AND nomprc='DEGTAR';
-- Fin Corps du script
