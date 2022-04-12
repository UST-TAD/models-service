package ust.tad.modelsservice.result;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.TechnologyAgnosticDeploymentModelService;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.*;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Artifact;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Component;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Operation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Property;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Relation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.exceptions.EntityNotFoundException;

@Service
public class ConfidenceService {

    @Autowired
    private TechnologyAgnosticDeploymentModelService service;
    
    public double calculateConfidence(UUID transformationProcessId) throws EntityNotFoundException {
        AnnotatedDeploymentModel tadm = 
            service.getTechnologyAgnosticDeploymentModelByTransformationProcessId(transformationProcessId);
        double sumOfEntities = calculateSumOfEntities(tadm);
        if (sumOfEntities == 0) {
            return 0D;
        }
        return calculateSumOfConfirmedEntities(tadm) / sumOfEntities;
    }

    private double calculateSumOfConfirmedEntities(AnnotatedDeploymentModel tadm) {        
        double result = 0;
        result += calculateSumOfConfirmedEntitiesForProperties(tadm.getProperties());
        for (Component component : tadm.getComponents()) {
            if (component instanceof AnnotatedComponent) {
                AnnotatedComponent annotated = (AnnotatedComponent) component;
                result += Boolean.TRUE.equals(annotated.isConfirmed()) ? 1 : 0;
            }
            result += calculateSumOfConfirmedEntitiesForProperties(component.getProperties());
            result += calculateSumOfConfirmedEntitiesForArtifacts(component.getArtifacts());
            result += calculateSumOfConfirmedEntitiesForOperations(component.getOperations());
        }
        for (Relation relation : tadm.getRelations()) {
            if (relation instanceof AnnotatedRelation) {
                AnnotatedRelation annotated = (AnnotatedRelation) relation;
                result += Boolean.TRUE.equals(annotated.isConfirmed()) ? 1 : 0;
            }
            result += calculateSumOfConfirmedEntitiesForProperties(relation.getProperties());
            result += calculateSumOfConfirmedEntitiesForOperations(relation.getOperations());
        }
        return result;
    }

    private int calculateSumOfConfirmedEntitiesForProperties(List<Property> properties) {
        int result = 0;
        for (Property property : properties) {
            if (property instanceof AnnotatedProperty) {
                AnnotatedProperty annotated = (AnnotatedProperty) property;
                result += Boolean.TRUE.equals(annotated.isConfirmed()) ? 1 : 0;
            }
        }
        return result;
    }

    private int calculateSumOfConfirmedEntitiesForArtifacts(List<Artifact> artifacts) {
        int result = 0;
        for (Artifact artifact : artifacts) {
            if (artifact instanceof AnnotatedArtifact) {
                AnnotatedArtifact annotated = (AnnotatedArtifact) artifact;
                result += Boolean.TRUE.equals(annotated.isConfirmed()) ? 1 : 0;
            }
        }
        return result;
    }

    private int calculateSumOfConfirmedEntitiesForOperations(List<Operation> operations) {
        int result = 0;
        for (Operation operation : operations) {
            if (operation instanceof AnnotatedOperation) {
                AnnotatedOperation annotated = (AnnotatedOperation) operation;
                result += Boolean.TRUE.equals(annotated.isConfirmed()) ? 1 : 0;
            }
            result += calculateSumOfConfirmedEntitiesForArtifacts(operation.getArtifacts());
        }
        return result;
    }


    private double calculateSumOfEntities(AnnotatedDeploymentModel tadm) {
        double result = 0;
        result += tadm.getProperties().size();
        result += tadm.getComponents().size();
        for (Component component : tadm.getComponents()) {
            result += component.getProperties().size();
            result += component.getArtifacts().size();
            result += component.getOperations().size();
            for (Operation operation : component.getOperations()) {
                result += operation.getArtifacts().size();
            }
        }
        result += tadm.getRelations().size();
        for (Relation relation : tadm.getRelations()) {
            result += relation.getProperties().size();
            result += relation.getOperations().size();
            for (Operation operation : relation.getOperations()) {
                result += operation.getArtifacts().size();
            }
        }
        return result;
    }

}
