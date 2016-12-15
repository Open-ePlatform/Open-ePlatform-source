<?xml version="1.0" encoding="ISO-8859-1" standalone="no"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	
	<xsl:import href="FlowTestCommon.sv.xsl"/>
	
	<xsl:include href="classpath://se/unlogic/hierarchy/core/utils/usergrouplist/UserGroupList.sv.xsl"/>
	
	<xsl:include href="FlowAdminModuleTemplates.xsl"/>
	
	<xsl:output encoding="ISO-8859-1" method="html" version="4.0"/>
	
	<xsl:variable name="java.flowNameCopySuffix"> (kopia)</xsl:variable>
	<xsl:variable name="java.fileMissing">Filen saknas</xsl:variable>
	
	<xsl:variable name="java.eventCopyFlowMessage">Kopierade version</xsl:variable>
	<xsl:variable name="java.eventUpdateIconMessage">Updaterade ikon till</xsl:variable>
	<xsl:variable name="java.eventUpdateNotificationsMessage">Uppdaterade notifikations inst�llningar</xsl:variable>
	<xsl:variable name="java.eventSortFlowMessage">Sorterade fr�gor och steg</xsl:variable>
	<xsl:variable name="java.eventImportFlowMessage">Importerade version</xsl:variable>
	<xsl:variable name="java.eventImportQueriesMessage">Importerade fr�gor</xsl:variable>
	<xsl:variable name="java.eventUpdatePDFMessage">Updaterade lokal blankett till</xsl:variable>
	<xsl:variable name="java.eventUpdateExternalPDFMessage">Updaterade extern blankett till</xsl:variable>
	<xsl:variable name="java.eventDeletePDFMessage">Tog bort blankett</xsl:variable>
	<xsl:variable name="java.eventFlowFamilyUpdatedMessage">Uppdaterade handl�ggare</xsl:variable>
	<xsl:variable name="java.eventFlowAddedMessage">Skapade version</xsl:variable>
	<xsl:variable name="java.eventFlowUpdatedMessage">Uppdaterade grundinformation</xsl:variable>
	<xsl:variable name="java.eventFlowDeletedMessage">Tog bort version</xsl:variable>
	<xsl:variable name="java.eventStepAddedMessage">Skapade steg</xsl:variable>
	<xsl:variable name="java.eventStepUpdatedMessage">Uppdaterade steg</xsl:variable>
	<xsl:variable name="java.eventStepDeletedMessage">Tog bort steg</xsl:variable>
	<xsl:variable name="java.eventQueryAddedMessage">Skapade fr�ga</xsl:variable>
	<xsl:variable name="java.eventQueryUpdatedMessage">Uppdaterade fr�ga</xsl:variable>
	<xsl:variable name="java.eventQueryDeletedMessage">Tog bort fr�ga</xsl:variable>
	<xsl:variable name="java.eventEvaluatorAddedMessage">Skapade regel</xsl:variable>
	<xsl:variable name="java.eventEvaluatorUpdatedMessage">Uppdaterade regel</xsl:variable>
	<xsl:variable name="java.eventEvaluatorDeletedMessage">Tog bort regel</xsl:variable>
	<xsl:variable name="java.eventStatusAddedMessage">Skapade status</xsl:variable>
	<xsl:variable name="java.eventStatusUpdatedMessage">Uppdaterade status</xsl:variable>
	<xsl:variable name="java.eventStatusDeletedMessage">Tog bort status</xsl:variable>
	<xsl:variable name="java.eventChangeFlowType">�ndrade kategori till</xsl:variable>
	<xsl:variable name="java.eventStatusSortMessage">Sorterade statusar</xsl:variable>
	
	<xsl:variable name="i18n.flowName">E-tj�nst</xsl:variable>
	
	<xsl:variable name="i18n.Flowslist.title">E-tj�nster</xsl:variable>
	<xsl:variable name="i18n.Flowlist.description">Nedan visas samtliga e-tj�nster i systemet som du har beh�righet att administrera.</xsl:variable>
	<xsl:variable name="i18n.typeOfFlow">Typ av e-tj�nst</xsl:variable>
	<xsl:variable name="i18n.internal">Intern</xsl:variable>
	<xsl:variable name="i18n.external">Extern</xsl:variable>
	<xsl:variable name="i18n.externalLink">L�nk till e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.OpenExternalFlow">�ppna e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.flowType">Kategori</xsl:variable>
	<xsl:variable name="i18n.flowCategory">Underkategori</xsl:variable>
	<xsl:variable name="i18n.steps">Steg</xsl:variable>
	<xsl:variable name="i18n.queries">Fr�gor</xsl:variable>
	<xsl:variable name="i18n.SubmittedInstances">Insk. �renden</xsl:variable>
	<xsl:variable name="i18n.NotSubmittedInstances">Ej insk. �renden</xsl:variable>
	<xsl:variable name="i18n.status">Status</xsl:variable>
	<xsl:variable name="i18n.noFlowsFound">Inga e-tj�nster hittades.</xsl:variable>
	<xsl:variable name="i18n.disabled">Inaktiverad</xsl:variable>
	<xsl:variable name="i18n.published">Publicerad</xsl:variable>
	<xsl:variable name="i18n.notPublished">Ej publicerad</xsl:variable>
	<xsl:variable name="i18n.deleteFlowDisabledIsPublished">Den h�r e-tj�nsten kan inte tas bort eftersom den �r publicerad.</xsl:variable>
	<xsl:variable name="i18n.deleteFlowDisabledHasInstances">Den h�r e-tj�nsten kan inte tas bort eftersom det finns en eller flera ans�kningar kopplade till den.</xsl:variable>
	<xsl:variable name="i18n.deleteFlowConfirm">Ta bort e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.deleteFlow.title">Ta bort e-tj�nsten</xsl:variable>
	
	<xsl:variable name="i18n.addFlow">L�gg till e-tj�nst</xsl:variable>

	<xsl:variable name="i18n.AddFlow.title">L�gg till e-tj�nst</xsl:variable>
	<xsl:variable name="i18n.AddFlow.submit">L�gg till</xsl:variable>
	
	<xsl:variable name="i18n.UpdateFlow.title">Uppdatera e-tj�nsten: </xsl:variable>
	<xsl:variable name="i18n.UpdateFlow.submit">Spara �ndringar</xsl:variable>

	<xsl:variable name="i18n.name">Namn</xsl:variable>
	<xsl:variable name="i18n.description">Beskrivning</xsl:variable>
	<xsl:variable name="i18n.shortDescription">Kort beskrivning</xsl:variable>
	<xsl:variable name="i18n.longDescription">L�ngre beskrivning</xsl:variable>
	<xsl:variable name="i18n.submittedMessage">Meddelande vid inl�mnat �rende</xsl:variable>
	<xsl:variable name="i18n.publishDate">Publiceringsdatum</xsl:variable>
	<xsl:variable name="i18n.unPublishDate">Avpubliceringsdatum</xsl:variable>
	<xsl:variable name="i18n.usePreview">Aktivera f�rhandsgranskning</xsl:variable>

	<xsl:variable name="i18n.contact.title">Kontaktuppgifter - Fr�gor om e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.contact.name">Namn</xsl:variable>
	<xsl:variable name="i18n.contact.email">E-post</xsl:variable>
	<xsl:variable name="i18n.contact.phone">Telefon</xsl:variable>
	<xsl:variable name="i18n.owner.title">Kontaktuppgifter - Personuppgiftsansvarig</xsl:variable>
	<xsl:variable name="i18n.owner.name">Namn</xsl:variable>
	<xsl:variable name="i18n.owner.email">E-post</xsl:variable>

	<xsl:variable name="i18n.SelectedFlowTypeNotFound">Den valda typen hittades inte!</xsl:variable>
	<xsl:variable name="i18n.FlowTypeAccessDenied">Du har inte beh�righet till den valda typen!</xsl:variable>

	<xsl:variable name="i18n.validation.requiredField" select="'Du beh�ver fylla i f�ltet:'"/>
	<xsl:variable name="i18n.validation.invalidFormat" select="'Felaktigt format p� f�ltet:'"/>
	<xsl:variable name="i18n.validation.tooShort" select="'F�r kort inneh�ll i f�ltet:'"/>
	<xsl:variable name="i18n.validation.tooLong" select="'F�r l�ngt inneh�ll i f�ltet:'"/>	
	<xsl:variable name="i18n.validation.unknownError" select="'Ok�nt fel p� f�ltet:'"/>
	<xsl:variable name="i18n.validation.unknownFault" select="'Ett ok�nt valideringsfel har uppst�tt.'"/>
	<xsl:variable name="i18n.enableFlow">Aktivera e-tj�nsten</xsl:variable>
	
	<xsl:variable name="i18n.baseInfo">Grundinformation</xsl:variable>
	<xsl:variable name="i18n.enabled">Aktiverad</xsl:variable>
	<xsl:variable name="i18n.icon">Ikon</xsl:variable>
	<xsl:variable name="i18n.PDFForm">Blankett</xsl:variable>
	<xsl:variable name="i18n.stepsAndQueries">Fr�gor och steg</xsl:variable>
	<xsl:variable name="i18n.statuses">Statusar</xsl:variable>
	<xsl:variable name="i18n.flowContainsNoSteps">Inga steg hittades.</xsl:variable>
	<xsl:variable name="i18n.flowHasNoStatuses">Inga statusar hittades.</xsl:variable>
	
	<xsl:variable name="i18n.updateFlowBaseInfo.title">Uppdatera e-tj�nstens grundinformation</xsl:variable>
		
	<xsl:variable name="i18n.stepAndQueryManipulationDisabledHasInstances">Det g�r inte att redigera fr�gorna och stegen f�r denna e-tj�nst eftersom det finns en eller flera ans�kningar kopplade till den.</xsl:variable>
	<xsl:variable name="i18n.stepAndQueryManipulationDisabledIsPublished">Det g�r inte att redigera fr�gorna och stegen f�r denna e-tj�nst eftersom den �r publicerad.</xsl:variable>
	<xsl:variable name="i18n.updateStep.title">Uppdatera steget</xsl:variable>
	<xsl:variable name="i18n.deleteStep.confirm.part1">Ta bort steget</xsl:variable>
	<xsl:variable name="i18n.deleteStep.confirm.part2">och eventuella fr�gor kopplade till steget?</xsl:variable>
	<xsl:variable name="i18n.deleteStep.title">Ta bort steget</xsl:variable>
	
	<xsl:variable name="i18n.updateQuery.title">Uppdatera fr�gan</xsl:variable>
	<xsl:variable name="i18n.deleteQuery.confirm">Ta bort fr�gan</xsl:variable>
	<xsl:variable name="i18n.deleteQuery.title">Ta bort fr�gan</xsl:variable>
	<xsl:variable name="i18n.addStep">L�gg till steg</xsl:variable>
	<xsl:variable name="i18n.addQuery">L�gg till fr�ga</xsl:variable>
	<xsl:variable name="i18n.sortStepsAndQueries">Sortera fr�gor och steg</xsl:variable>
	
	<xsl:variable name="i18n.AddQueryDescriptor.title">L�gg till fr�ga</xsl:variable>
	<xsl:variable name="i18n.step">Steg</xsl:variable>
	<xsl:variable name="i18n.queryType">Fr�getyp</xsl:variable>
	<xsl:variable name="i18n.AddQueryDescriptor.submit">L�gg till</xsl:variable>
	<xsl:variable name="i18n.SelectedStepNotFound">Det valda steget hittades inte.</xsl:variable>
	<xsl:variable name="i18n.SelectedQueryTypeNotFound">Den valda fr�getypen hittades inte.</xsl:variable>
	
	<xsl:variable name="i18n.AddStep.title">L�gg till steg</xsl:variable>
	<xsl:variable name="i18n.AddStep.submit">L�gg till</xsl:variable>
	<xsl:variable name="i18n.UpdateStep.title">Uppdatera steget: </xsl:variable>
	<xsl:variable name="i18n.UpdateStep.submit">Spara �ndringar</xsl:variable>
	
	<xsl:variable name="i18n.AddStatus.title">L�gg till status</xsl:variable>
	<xsl:variable name="i18n.AddStatus.submit">L�gg till</xsl:variable>
	<xsl:variable name="i18n.UpdateStatus.title">Uppdatera status: </xsl:variable>
	<xsl:variable name="i18n.UpdateStatus.submit">Spara �ndringar</xsl:variable>
	<xsl:variable name="i18n.isUserMutable">Till�t anv�ndare att �ndra ans�kningar med denna status</xsl:variable>
	<xsl:variable name="i18n.isUserDeletable">Till�t anv�ndare att ta bort ans�kningar med denna status</xsl:variable>
	<xsl:variable name="i18n.isAdminMutable">Till�t handl�ggare att �ndra ans�kningar med denna status</xsl:variable>
	<xsl:variable name="i18n.isAdminDeletable">Till�t handl�ggare att ta bort �renden med denna status</xsl:variable>
	<xsl:variable name="i18n.defaultStatusMappings.title">Statusmappningar</xsl:variable>
	<xsl:variable name="i18n.defaultStatusMappings.description">Anv�nd denna status vid f�ljande h�ndelser.</xsl:variable>
	<xsl:variable name="i18n.managingTime">Handl�ggningstid</xsl:variable>
	<xsl:variable name="i18n.required">obligatorisk</xsl:variable>
	<xsl:variable name="i18n.managingTime.description">Antalet dagar som �renden f�r befinna sig i denna status innan de f�r handl�ggaren blir markerad som akuta.</xsl:variable>
	
	<xsl:variable name="i18n.deleteStatusDisabledHasInstances">Den h�r statusen kan inte tas bort eftersom det finns en eller flera ans�kningar kopplade till den.</xsl:variable>
	<xsl:variable name="i18n.updateStatus.link.title">Uppdatera statusen</xsl:variable>
	<xsl:variable name="i18n.deleteStatus.link.title">Ta bort statusen</xsl:variable>
	<xsl:variable name="i18n.deleteStatus.confirm">Ta bort statusen</xsl:variable>
	<xsl:variable name="i18n.addStatus">L�gg till status</xsl:variable>
	
	<xsl:variable name="i18n.statusContentType.title">Inneh�ll</xsl:variable>
	<xsl:variable name="i18n.statusContentType.description">V�lj vilken typ av �renden som den h�r statusen kommer att inneh�lla.</xsl:variable>
	<xsl:variable name="i18n.contentType.NEW">Sparade men ej inskickade �renden</xsl:variable>
	<xsl:variable name="i18n.contentType.WAITING_FOR_MULTISIGN">V�ntar p� flerpartssignering</xsl:variable>
	<xsl:variable name="i18n.contentType.WAITING_FOR_PAYMENT">V�ntar p� betalning</xsl:variable>
	<xsl:variable name="i18n.contentType.SUBMITTED">Inskickade �renden</xsl:variable>
	<xsl:variable name="i18n.contentType.IN_PROGRESS">�renden under behandling</xsl:variable>
	<xsl:variable name="i18n.contentType.WAITING_FOR_COMPLETION">V�ntar p� komplettering</xsl:variable>
	<xsl:variable name="i18n.contentType.ARCHIVED">Avslutade �renden</xsl:variable>

	<xsl:variable name="i18n.contentType">Inneh�ll</xsl:variable>
	<xsl:variable name="i18n.permissions">Beh�righeter</xsl:variable>
	
	<xsl:variable name="i18n.updateFlowIcon.link.title">Uppdatera ikon</xsl:variable>
	
	<xsl:variable name="i18n.UpdateFlowIcon.title">Uppdatera ikon f�r e-tj�nsten:</xsl:variable>
	<xsl:variable name="i18n.currentIcon">Aktuell ikon</xsl:variable>
	<xsl:variable name="i18n.defaultIcon">(standard ikon)</xsl:variable>
	<xsl:variable name="i18n.restoreDefaultIcon">�terst�ll standard ikon</xsl:variable>
	<xsl:variable name="i18n.uploadNewIcon">Ladda upp ny ikon (png, jpg, gif eller bmp format)</xsl:variable>
	<xsl:variable name="i18n.UpdateFlowIcon.submit">Spara �ndringar</xsl:variable>
	<xsl:variable name="i18n.UnableToParseRequest">Det gick inte att tolka informationen fr�n din webbl�sare.</xsl:variable>
	<xsl:variable name="i18n.UnableToParseIcon">Den gick att tolka ikonen.</xsl:variable>
	<xsl:variable name="i18n.InvalidIconFileFormat">Felaktig filformat endast ikoner i png, jpg, gif eller bmp format �r till�tna.</xsl:variable>
	
	<xsl:variable name="i18n.addFlowPDF.link.title">L�gg till blankett</xsl:variable>
	<xsl:variable name="i18n.updateFlowPDF.link.title">Uppdatera blankett</xsl:variable>
	<xsl:variable name="i18n.deleteFlowPDF.link.title">Ta bort blankett</xsl:variable>
	<xsl:variable name="i18n.UpdateFlowPDF.title">Uppdatera blankett f�r e-tj�nsten:</xsl:variable>
	<xsl:variable name="i18n.UpdateFlowPDF.description">V�lj antigen en PDF fil att ladda upp eller l�nka till en extern PDF</xsl:variable>
	<xsl:variable name="i18n.uploadNewPDFForm">Ladda upp ny blankett</xsl:variable>
	<xsl:variable name="i18n.externalPDF">Extern PDF</xsl:variable>
	<xsl:variable name="i18n.UpdateFlowPDF.submit">Spara �ndringar</xsl:variable>
	<xsl:variable name="i18n.UnableToStoreFile">Det gick inte att spara blanketten.</xsl:variable>
	<xsl:variable name="i18n.InvalidPDFFormFileFormat">Felaktig filformat endast blanketter i PDF format �r till�tna.</xsl:variable>
	<xsl:variable name="i18n.NoAttachedFile">Du har inte bifogat n�gon fil.</xsl:variable>
	
	<xsl:variable name="i18n.defaultQueryState">Standardl�ge</xsl:variable>
	<xsl:variable name="i18n.defaultQueryState.title">Standardl�ge</xsl:variable>
	<xsl:variable name="i18n.defaultQueryState.description">V�lj vilket standardl�ge som fr�gan skall ha.</xsl:variable>
	<xsl:variable name="i18n.queryState.VISIBLE">Valfri</xsl:variable>
	<xsl:variable name="i18n.queryState.VISIBLE_REQUIRED">Obligatorisk</xsl:variable>
	<xsl:variable name="i18n.queryState.HIDDEN">Dold</xsl:variable>

	<xsl:variable name="i18n.SortFlow.title">Sortera fr�gor och steg</xsl:variable>
	<xsl:variable name="i18n.SortFlow.description">Observera att en fr�ga som har regler inte kan placeras efter de fr�gor som reglerna p�verkar.</xsl:variable>
	<xsl:variable name="i18n.SortFlow.submit">Spara �ndringar</xsl:variable>
	<xsl:variable name="i18n.MoveStep">Flytta steg</xsl:variable>
	<xsl:variable name="i18n.MoveQuery">Flytta fr�ga</xsl:variable>
	
	<xsl:variable name="i18n.NoStepSortindex">Det gick inte att hitta sorteringsindex f�r alla steg.</xsl:variable>
	<xsl:variable name="i18n.NoQueryDescriptorSortindex">Det gick inte att hitta sorteringsindex f�r alla fr�gor.</xsl:variable>
	<xsl:variable name="i18n.InvalidQuerySortIndex">En eller flera fr�gor har felaktigt sorteringsindex. Fr�gor med regler f�r inte ligga efter de fr�gor som de p�verkar. De fr�gor som p�verkas av regler f�r inte ligga f�re fr�gan med regeln.</xsl:variable>
	
	<xsl:variable name="i18n.UnableToFindStepsForAllQueries">Det gick inte att koppla alla fr�gor till steg.</xsl:variable>
	<xsl:variable name="i18n.updateEvaluator.title">Uppdatera regel</xsl:variable>
	<xsl:variable name="i18n.deleteEvaluator.confirm">Ta bort regel</xsl:variable>
	<xsl:variable name="i18n.deleteEvaluator.title">Ta bort regel</xsl:variable>
	<xsl:variable name="i18n.addEvaluator.title">L�gg till regel kopplad till fr�gan</xsl:variable>
	<xsl:variable name="i18n.AddEvaluatorDescriptor.title">L�gg till regel kopplad till fr�gan</xsl:variable>
	<xsl:variable name="i18n.evaluatorType">Regeltyp</xsl:variable>
	<xsl:variable name="i18n.AddEvaluatorDescriptor.submit">L�gg till regel</xsl:variable>
	
	<xsl:variable name="i18n.SelectedEvaluatorTypeNotFound">Den valda regeltypen hittades inte</xsl:variable>
	<xsl:variable name="i18n.evaluatorTypeID">Regeltyp</xsl:variable>
	<xsl:variable name="i18n.flowVersion">version</xsl:variable>
	<xsl:variable name="i18n.versions">Versioner</xsl:variable>
	<xsl:variable name="i18n.version.title">Version</xsl:variable>
	<xsl:variable name="i18n.flowHasNoOtherVersions">Det finns inga andra versioner av denna e-tj�nst.</xsl:variable>
	
	<xsl:variable name="i18n.addNewVersion">L�gg till en ny version</xsl:variable>
	<xsl:variable name="i18n.createNewFlow">Skapa en ny e-tj�nst</xsl:variable>
	
	<xsl:variable name="i18n.deleteFlowFamilyDisabledHasInstances">Det g�r inte att ta bort den h�r e-tj�nsten f�r en eller flera av dess versioner har ans�kningar kopplade till sig.</xsl:variable>
	<xsl:variable name="i18n.deleteFlowFamilyDisabledIsPublished">Det g�r inte att ta bort den h�r e-tj�nsten f�r en eller flera av dess versioner �r publicerade.</xsl:variable>
	<xsl:variable name="i18n.deleteFlowFamilyConfirm">�r du s�ker p� att du vill ta bort samtliga versioner av e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.deleteFlowFamily.title">Ta bort samtliga versioner av e-tj�nsten</xsl:variable>
	
	<xsl:variable name="i18n.versions.description">Tabellen nedan visar samtliga versioner av denna e-tj�nst. Markera en e-tj�nst i listan f�r att skapa en ny version eller en helt ny e-tj�nst baserat p� den valda versionen.</xsl:variable>
	<xsl:variable name="i18n.FlowNotFound">Den beg�rda e-tj�nsten hittades inte.</xsl:variable>
	<xsl:variable name="i18n.unknownQueryType">Ok�nd fr�getyp</xsl:variable>
	<xsl:variable name="i18n.unknownEvaluatorType">Ok�nd regeltyp</xsl:variable>
	<xsl:variable name="i18n.administrateStandardStatuses">Adm. standardstatusar</xsl:variable>
	<xsl:variable name="i18n.ListStandardStatuses.title">Standardstatusar</xsl:variable>
	<xsl:variable name="i18n.ListStandardStatuses.description">Nedan visas samtliga standardstatusar i systemet.</xsl:variable>
	<xsl:variable name="i18n.noStandardStatusesFound">Inga standardstatusar hittades.</xsl:variable>
	<xsl:variable name="i18n.addStandardStatus">L�gg till standardstatus</xsl:variable>
	<xsl:variable name="i18n.AddStandardStatus.title">L�gg till standardstatus</xsl:variable>
	<xsl:variable name="i18n.UpdateStandardStatus.title">Uppdatera standardstatus</xsl:variable>
	<xsl:variable name="i18n.updateStandardStatus.link.title">Uppdatera standardstatus</xsl:variable>
	<xsl:variable name="i18n.deleteStandardStatus.confirm">Ta bort standardstatus</xsl:variable>
	<xsl:variable name="i18n.deleteStandardStatus.link.title">Ta bort standardstatus</xsl:variable>
	<xsl:variable name="i18n.addStandardStatuses">L�gg till standardstatusar</xsl:variable>
	<xsl:variable name="i18n.RequestedFlowFamilyNotFound">Den beg�rda e-tj�nsten hittades inte.</xsl:variable>
	<xsl:variable name="i18n.FlowFamilyCannotBeDeleted">Den beg�rda e-tj�nsten kan inte tas bort d� en eller flera versioner av den �r publicerade eller har ans�kningar knuta till sig.</xsl:variable>
	<xsl:variable name="i18n.testFlow">Testa e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.downloadxsd.title">Ladda ner XSD schema</xsl:variable>
	
	<xsl:variable name="i18n.tags">Extra s�kord (ett per rad)</xsl:variable>
	<xsl:variable name="i18n.checks.title">Krav f�r e-tj�nsten (ett per rad)</xsl:variable>
	<xsl:variable name="i18n.checks">Krav f�r e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.administrateFlowTypes">Administrera kategorier</xsl:variable>
	<xsl:variable name="i18n.ListFlowTypes.title">Kategorier</xsl:variable>
	<xsl:variable name="i18n.ListFlowTypes.description">Nedan visas en lista p� de kategorier som du har beh�righet att komma �t.</xsl:variable>
	<xsl:variable name="i18n.categories">Underkategorier</xsl:variable>
	<xsl:variable name="i18n.noFlowTypesFound">Inga kategorier hittades</xsl:variable>
	<xsl:variable name="i18n.addFlowType">L�gg till kategori</xsl:variable>
	<xsl:variable name="i18n.flowFamilies">E-tj�nster</xsl:variable>
	<xsl:variable name="i18n.deleteFlowTypeDisabledHasFlows">Den h�r kategorin g�r inte att ta bort f�r den har en eller flera e-tj�nster kopplade till sig!</xsl:variable>
	<xsl:variable name="i18n.deleteFlowType">Ta bort kategori</xsl:variable>
	<xsl:variable name="i18n.showFlowType">Visa kategori</xsl:variable>
	<xsl:variable name="i18n.updateFlowType">Uppdatera kategori</xsl:variable>
	
	<xsl:variable name="i18n.allowedGroups">Grupper:</xsl:variable>
	<xsl:variable name="i18n.allowedUsers">Anv�ndare:</xsl:variable>

	<xsl:variable name="i18n.allowedQueryTypes">Fr�getyper</xsl:variable>
	<xsl:variable name="i18n.noCategory">Ingen underkategori</xsl:variable>
	<xsl:variable name="i18n.noCategories">Det finns inga underkategorier f�r den h�r kategorin</xsl:variable>
	<xsl:variable name="i18n.updateCategory">Uppdatera underkategorin</xsl:variable>
	<xsl:variable name="i18n.deleteCategory">Ta bort underkategorin</xsl:variable>
	<xsl:variable name="i18n.addCategory">L�gg till underkategori</xsl:variable>
	<xsl:variable name="i18n.noAllowedQueryTypes">Inga fr�getyper till�ts f�r denna kategori.</xsl:variable>
	<xsl:variable name="i18n.AddFlowType.title">L�gg till kategori</xsl:variable>
	<xsl:variable name="i18n.AddFlowType.submit">L�gg till</xsl:variable>
	<xsl:variable name="i18n.UpdateFlowType.title">Uppdatera kategori</xsl:variable>
	<xsl:variable name="i18n.UpdateFlowType.submit">Spara �ndringar</xsl:variable>
	<xsl:variable name="i18n.AddCategory.title">L�gg till underkategori</xsl:variable>
	<xsl:variable name="i18n.AddCategory.submit">L�gg till</xsl:variable>
	<xsl:variable name="i18n.UpdateCategory.title">Uppdatera underkategorin</xsl:variable>
	<xsl:variable name="i18n.UpdateCategory.submit">Spara �ndringar</xsl:variable>
	
	<xsl:variable name="i18n.Managers">Handl�ggare</xsl:variable>
	<xsl:variable name="i18n.ManagersDescription">F�ljande grupper och anv�ndare f�r handl�gga �renden f�r denna e-tj�nst.</xsl:variable>
	<xsl:variable name="i18n.UpdateFlowFamilyManagers">V�lj handl�ggare</xsl:variable>
	<xsl:variable name="i18n.NoManagers">Inga handl�ggare har �tkomst till �renden f�r den h�r e-tj�nsten.</xsl:variable>
	<xsl:variable name="i18n.UpdateManagers.title">Uppdatera handl�ggare f�r e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.UpdateManagers.submit">Spara �ndringar</xsl:variable>
	
	<xsl:variable name="i18n.AddFlowCategoryNotFound">Den beg�rda underkategorin hittades inte.</xsl:variable>
	<xsl:variable name="i18n.AddCategoryFailedFlowTypeNotFound">Den beg�rda kategorin hittades inte.</xsl:variable>
	<xsl:variable name="i18n.UpdateFailedCategoryNotFound">Den beg�rda underkategorin hittades inte.</xsl:variable>
	<xsl:variable name="i18n.DeleteFailedCategoryNotFound">Den beg�rda underkategorin hittades inte.</xsl:variable>
	<xsl:variable name="i18n.UpdateFailedFlowTypeNotFound">Den beg�rda e-tj�nsten hittades inte.</xsl:variable>
	<xsl:variable name="i18n.DeleteFailedFlowTypeNotFound">Den beg�rda e-tj�nsten hittades inte.</xsl:variable>
	<xsl:variable name="i18n.ShowFailedFlowTypeNotFound">Den beg�rda e-tj�nsten hittades inte.</xsl:variable>
	<xsl:variable name="i18n.UpdateFailedStepNotFound">Det beg�rda steget hittades inte.</xsl:variable>
	<xsl:variable name="i18n.DeleteFailedStepNotFound">Det beg�rda steget hittades inte.</xsl:variable>
	<xsl:variable name="i18n.UpdateFailedQueryDescriptorNotFound">Den beg�rda fr�gan hittades inte.</xsl:variable>
	<xsl:variable name="i18n.DeleteFailedQueryDescriptorNotFound">Den beg�rda fr�gan hittades inte.</xsl:variable>
	<xsl:variable name="i18n.UpdateFailedEvaluatorDescriptorNotFound">Den beg�rda regeln hittades inte.</xsl:variable>
	<xsl:variable name="i18n.DeleteFailedEvaluatorDescriptorNotFound">Den beg�rda regeln hittades inte.</xsl:variable>
	<xsl:variable name="i18n.UpdateFailedStatusNotFound">Den beg�rda statusen hittades inte.</xsl:variable>
	<xsl:variable name="i18n.DeleteFailedStatusNotFound">Den beg�rda statusen hittades inte.</xsl:variable>
	<xsl:variable name="i18n.UpdateFailedStandardStatusNotFound">Den beg�rda standardstatusen hittades inte.</xsl:variable>
	<xsl:variable name="i18n.DeleteFailedStandardStatusNotFound">Den beg�rda standardstatusen hittades inte.</xsl:variable>
	<xsl:variable name="i18n.FlowTypeQueryTypeAccessDenied">Den h�r typen av e-tj�nster har inte beh�righet att anv�nda den valda fr�getypen.</xsl:variable>
	<xsl:variable name="i18n.requireAuthentication">Kr�v inloggning</xsl:variable>
	<xsl:variable name="i18n.requirersAuthentication">Kr�ver inloggning</xsl:variable>
	<xsl:variable name="i18n.requireSigning">Kr�v signering</xsl:variable>
	<xsl:variable name="i18n.requiresSigning">Kr�ver signering</xsl:variable>
	<xsl:variable name="i18n.MissingDefaultStatusMapping">E-tj�nsten g�r inte att publicera d� dess statusar inte inneh�ller samtliga obligatoriska statusmappningar. Klicka ur "Aktivera" e-tj�nsten" och spara g� sedan tillbaka till e-tj�nst�versikten f�r att kontrollera statusarna.</xsl:variable>
	
	<xsl:variable name="i18n.UnauthorizedManagerUserError.Part1">Anv�ndaren</xsl:variable>
	<xsl:variable name="i18n.UnauthorizedManagerUserError.Part2">handl�gger aktiva �renden f�r den h�r e-tj�nsten och f�r d�rf�r inte plockas bort</xsl:variable>
	
	<xsl:variable name="i18n.exportFlow.title">Exportera e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.EvaluatorExportException.Part1">Ett fel uppstod n�r regelen</xsl:variable>
	<xsl:variable name="i18n.EvaluatorExportException.Part2">skulle exporteras, kontakta administrat�ren f�r mer information.</xsl:variable>
	<xsl:variable name="i18n.QueryExportException.Part1">Ett fel uppstod n�r fr�gan</xsl:variable>
	<xsl:variable name="i18n.QueryExportException.Part2">skulle exporteras, kontakta administrat�ren f�r mer information.</xsl:variable>
	<xsl:variable name="i18n.FlowImportFlowFamlilyNotFound">Den beg�rda e-tj�nsten hittades inte.</xsl:variable>
	
	<xsl:variable name="i18n.SelectImportTargetType.title">V�lj kategori</xsl:variable>
	<xsl:variable name="i18n.SelectImportTargetType.description">V�lj vilken kategori av e-tj�nst du vill importera.</xsl:variable>
	<xsl:variable name="i18n.ImportFlow.NewVersion.title">Importera ny version av e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.ImportFlow.NewVersion.description">Anv�nd formul�ret nedan f�r att importera en ny version. Filen du v�ljer beh�ver vara av typen oeflow.</xsl:variable>
	<xsl:variable name="i18n.ImportFlow.NewFamily.title">Importera ny e-tj�nst av typen</xsl:variable>
	<xsl:variable name="i18n.ImportFlow.NewFamily.description">Anv�nd formul�ret nedan f�r att importera en ny e-tj�nst. Filen du v�ljer beh�ver vara av typen oeflow.</xsl:variable>
	<xsl:variable name="i18n.selectFlowFile">V�lj fil</xsl:variable>	
	
	<xsl:variable name="i18n.ImportFlow.submit">Importera</xsl:variable>

	<xsl:variable name="i18n.importFlow">Importera e-tj�nst</xsl:variable>
	<xsl:variable name="i18n.importNewFlowVersion">Importera en ny version</xsl:variable>
	
	<xsl:variable name="i18n.QueryTypeNotAllowedInFlowTypeValidationError.Part1">Fr�gan</xsl:variable>
	<xsl:variable name="i18n.QueryTypeNotAllowedInFlowTypeValidationError.Part2">�r av en typ som inte till�ts i e-tj�nster av kategori</xsl:variable>
	<xsl:variable name="i18n.QueryTypeNotAllowedInFlowTypeValidationError.Part3">.</xsl:variable>
	
	<xsl:variable name="i18n.EvaluatorTypeNotFound.Part1">Regeltypen f�r regeln</xsl:variable>
	<xsl:variable name="i18n.EvaluatorTypeNotFound.Part2">hittades inte.</xsl:variable>
	
	<xsl:variable name="i18n.QueryTypeNotFound.Part1">Fr�getypen f�r fr�gan</xsl:variable>
	<xsl:variable name="i18n.QueryTypeNotFound.Part2">hittades inte.</xsl:variable>
	
	<xsl:variable name="i18n.EvaluatorImportException.Part1">Ett fel uppstod n�r regeln</xsl:variable>
	<xsl:variable name="i18n.EvaluatorImportException.Part2">skulle importeras, kontakta administrat�ren f�r mer information.</xsl:variable>
	<xsl:variable name="i18n.QueryImportException.Part1">Ett fel uppstod n�r fr�gan</xsl:variable>
	<xsl:variable name="i18n.QueryImportException.Part2">skulle importeras, kontakta administrat�ren f�r mer information.</xsl:variable>
	
	<xsl:variable name="i18n.InvalidFileExtension.Part1">Filen</xsl:variable>
	<xsl:variable name="i18n.InvalidFileExtension.Part2">�r av en felaktig filtyp.</xsl:variable>
	<xsl:variable name="i18n.InvalidFileExtension.Part3">F�ljande filtyper �r till�tna:</xsl:variable>
	
	<xsl:variable name="i18n.UnableToParseFile.part1">Den gick inte att tolka inneh�llet i filen </xsl:variable>
	<xsl:variable name="i18n.UnableToParseFile.part2">.</xsl:variable>
	<xsl:variable name="i18n.UnauthorizedManagerUserError.MemberOfGroups">medlem i</xsl:variable>
	
	<xsl:variable name="i18n.showSubmitSurvey">Visa anv�ndarunders�kning</xsl:variable>
	<xsl:variable name="i18n.hideSubmitStepText">G�m steget "Skicka in" i steg�verblicken</xsl:variable>
	
	<xsl:variable name="i18n.FlowSurveysTitle">Anv�ndarunders�kningar</xsl:variable>
	
	<xsl:variable name="i18n.UpdateNotificationSettings">�ndra inst�llningar</xsl:variable>
	<xsl:variable name="i18n.Notifications">Notifieringar</xsl:variable>
	<xsl:variable name="i18n.UpdateNotifications.title">Notifieringsinst�llningar f�r e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.UpdateNotifications.reset.confirm">�r du helt s�ker p� att du vill �terst�lla standardv�rden f�r notifieringar f�r denna e-tj�nst?</xsl:variable>
	<xsl:variable name="i18n.UpdateNotifications.reset">�terst�ll standardv�rden</xsl:variable>
	<xsl:variable name="i18n.UpdateNotifications.submit">Spara �ndringar</xsl:variable>
	
	<xsl:variable name="i18n.StatisticsSettings">Statistik inst�llningar</xsl:variable>
	<xsl:variable name="i18n.StatisticsMode.None">Generera ingen statistik</xsl:variable>
	<xsl:variable name="i18n.StatisticsMode.Internal">Generera statistik men visa den endast f�r interna anv�ndare</xsl:variable>
	<xsl:variable name="i18n.StatisticsMode.Public">Generera statistik och visa den publikt</xsl:variable>

	<xsl:variable name="i18n.skipOverview">Hoppa �ver �versiktsidan</xsl:variable>
	
	<xsl:variable name="i18n.MissingDefaultStatusMappingForMultiSigning">E-tj�nsten g�r inte att publicera d� den saknar statusmappning f�r flerpartssignering. Klicka ur "Aktivera" e-tj�nsten" och spara g� sedan tillbaka till e-tj�nst�versikten f�r att kontrollera statusarna.</xsl:variable>
	
	<xsl:variable name="i18n.hasNoPDFForm">Den h�r e-tj�nsten har ingen PDF blankett kopplad till sig.</xsl:variable>
	<xsl:variable name="i18n.MayNotRemovePDFFormIfNoSteps">Du f�r inte ta bort blanketten om e-tj�nsten �r aktiverad och saknar steg</xsl:variable>
	<xsl:variable name="i18n.MayNotAddPDFFormIfOverviewSkipIsSet">Du kan inte l�gga till en blankett om e-tj�nsten �r inst�lld p� att hoppa �ver �versiktsidan</xsl:variable>
	<xsl:variable name="i18n.MayNotSetOverviewIfPDFIsSet">Du kan inte hoppa �ver �versiktsidan om det finns en blankett kopplad till e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.MayNotSetOverviewIfPDFIsSet.description">ej m�jligt om det finns en blankett kopplad till e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.FlowHasNoContent">Du kan inte aktivera e-tj�nsten d� den inte har n�gra steg eller har n�gon blankett</xsl:variable>
	<xsl:variable name="i18n.FlowHasNoStepsAndOverviewSkipIsSet">Du kan inte aktivera e-tj�nsten med alterntivet 'hoppa �ver �versiktsidan' satt d� e-tj�nsten inte inneh�ller n�gra steg.</xsl:variable>
	<xsl:variable name="i18n.FlowFamilyAliasAlreadyInUse">Kortnamnet</xsl:variable>
	<xsl:variable name="i18n.FlowFamilyAliasAlreadyInUse2">anv�nds redan av e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.FlowFamilyAliasAlreadyInUseBySystem">anv�nds redan av en annan del av systemet.</xsl:variable>
	<xsl:variable name="i18n.aliases.title">Kortnamn f�r e-tj�nsten (ett per rad ex. bygga eller skola, giltiga tecken �r A-Z a-z 0-9 _- )</xsl:variable>
	<xsl:variable name="i18n.aliases">Kortnamn f�r e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.LacksPublishAccess">Du saknar publiceringsr�ttigheter och kan d�rf�r inte �ndra publiceringsdatum eller aktivera e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.Filter">S�k</xsl:variable>
	<xsl:variable name="i18n.link">l�nk</xsl:variable>

	<xsl:variable name="i18n.ExportQuery.title">Exportera fr�gan</xsl:variable>
	<xsl:variable name="i18n.ExportFailedQueryDescriptorNotFound">Den beg�rda fr�gan hittades inte.</xsl:variable>
	
	<xsl:variable name="i18n.ImportQueries.title">Importera fr�gor i e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.ImportQueries.description">Anv�nd formul�ret nedan f�r att importera en eller flera fr�gor i det valda steget. Filerna du v�ljer m�sta vara av typen oequery.</xsl:variable>
	<xsl:variable name="i18n.SelectQueryFiles">V�lj filer</xsl:variable>
	<xsl:variable name="i18n.ImportQueries.submit">Importera</xsl:variable>
	<xsl:variable name="i18n.ImportQueries">Importera fr�gor</xsl:variable>
	
	<xsl:variable name="i18n.Events.Title">�ndringslogg</xsl:variable>
	<xsl:variable name="i18n.Events.Full.Title">Fullst�ndig �ndringslogg f�r</xsl:variable>
	<xsl:variable name="i18n.Events.Description">Tabellen nedan visar de senaste �ndringarna f�r samtliga versioner av denna e-tj�nst.</xsl:variable>
	<xsl:variable name="i18n.Events.Full.Description">Tabellen nedan visar alla �ndringarna f�r samtliga versioner av denna e-tj�nst.</xsl:variable>
	<xsl:variable name="i18n.Events.message">H�ndelse</xsl:variable>
	<xsl:variable name="i18n.Events.poster">Anv�ndare</xsl:variable>
	<xsl:variable name="i18n.Events.added">Tidpunkt</xsl:variable>
	<xsl:variable name="i18n.Events.FlowFamilyHasNoEvents">Inga �ndringar hittades.</xsl:variable>
	<xsl:variable name="i18n.Events.ShowAll">Visa alla �ndringar</xsl:variable>
	
	<xsl:variable name="i18n.FileSizeLimitExceeded.part1">Filen </xsl:variable>
	<xsl:variable name="i18n.FileSizeLimitExceeded.part2"> har en storlek p� </xsl:variable>
	<xsl:variable name="i18n.FileSizeLimitExceeded.part3"> vilket �verskrider den maximalt till�tna filstorleken p� </xsl:variable>
	<xsl:variable name="i18n.FileSizeLimitExceeded.part4">.</xsl:variable>
	
	<xsl:variable name="i18n.RequestSizeLimitExceeded.part1">Du bifogade totalt </xsl:variable>
	<xsl:variable name="i18n.RequestSizeLimitExceeded.part2"> filer och den max till�tna storleken �r </xsl:variable>
	<xsl:variable name="i18n.RequestSizeLimitExceeded.part3">!</xsl:variable>

	<xsl:variable name="i18n.FlowType.AdminAccess.Title">Administration</xsl:variable>
	<xsl:variable name="i18n.FlowType.AdminAccess.Description">Ange vilka anv�ndare och grupper som ska ha beh�righet att bygga och administrera e-tj�nster i den h�r kategorin.</xsl:variable>
	
	<xsl:variable name="i18n.onlyModuleAdminAccess">Endast globala administat�rer har beh�righet att bygga och administrera e-tj�nster i den h�r kategorin.</xsl:variable>
	
	<xsl:variable name="i18n.FlowType.UserAccess.Title">�tkomst till e-tj�nster</xsl:variable>
	<xsl:variable name="i18n.FlowType.UserAccess.Description">Om du vill begr�nsa �tkomsten till e-tj�nsterna inom denna kategori s� kan du kryssa i rutan och sedan ange vilka anv�ndare och grupper som ska f� komma �t e-tj�nsterna.</xsl:variable>
	<xsl:variable name="i18n.enableAccessFiltering">Aktivera begr�nsad �tkomst</xsl:variable>
	
	<xsl:variable name="i18n.noUserAccess">Inga anv�ndare eller grupper har �tkomst till e-tj�nster i denna kategori.</xsl:variable>
	
	<xsl:variable name="i18n.noAccessFilter">Samtliga anv�ndare har �tkomst till e-tj�nsterna i denna kategori.</xsl:variable>

	<xsl:variable name="i18n.hideManagerDetails">D�lj uppgifter om handl�ggare</xsl:variable>
	
	<xsl:variable name="i18n.popularity.boost">Extra antal �renden vid ber�kning av popularitet</xsl:variable>
	
	<xsl:variable name="i18n.startButtonText">Anpassad text p� startknapp (max 24 tecken)</xsl:variable>
	
	<xsl:variable name="i18n.FlowType.Icon">Ikon</xsl:variable>
	
	<xsl:variable name="i18n.FlowType.UploadIcon">Till�tna filtyper: jpg, png, gif, bmp</xsl:variable>
	<xsl:variable name="i18n.FlowType.DeleteIcon">Ta bort</xsl:variable>
	<xsl:variable name="i18n.FlowType.IconColor">Bakgrundsf�rg</xsl:variable>
	<xsl:variable name="i18n.FlowType.iconColor">bakgrundsf�rg</xsl:variable>
	
	<xsl:variable name="i18n.All">Alla</xsl:variable>
	<xsl:variable name="i18n.Published">Publicerade</xsl:variable>
	<xsl:variable name="i18n.Unpublished">Ej publicerade</xsl:variable>
	<xsl:variable name="i18n.statusFilter">Visa</xsl:variable>
	
	<xsl:variable name="i18n.hideFromOverview">D�lj e-tj�nsten p� f�rstasidan</xsl:variable>
	<xsl:variable name="i18n.hideInternalMessages">D�lj interna noteringar i handl�ggargr�nssnittet</xsl:variable>
	
	<xsl:variable name="i18n.ChangeFlowType.linkTitle">�ndra kategori p� e-tj�nsten</xsl:variable>
	<xsl:variable name="i18n.ChangeFlowType.title">�ndra kategori p� e-tj�nst</xsl:variable>
	<xsl:variable name="i18n.ChangeFlowType.description">H�r kan du �ndra kategori p� e-tj�nsten. T�nk p� att alla versioner p�verkas.</xsl:variable>
	<xsl:variable name="i18n.ChangeFlowType.ChooseNewFlowType">V�lj ny kategori</xsl:variable>
	<xsl:variable name="i18n.ChangeFlowType.submit">�ndra kategori</xsl:variable>
	
	<xsl:variable name="i18n.sortStatuses">Sortera statusar</xsl:variable>
	<xsl:variable name="i18n.SortFlowStatuses.title">Sortera statusar</xsl:variable>
	<xsl:variable name="i18n.MoveStatus">Flytta status</xsl:variable>
</xsl:stylesheet>
