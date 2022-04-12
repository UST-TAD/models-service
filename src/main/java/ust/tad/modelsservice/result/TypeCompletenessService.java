package ust.tad.modelsservice.result;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.TechnologyAgnosticDeploymentModelService;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.annotatedentities.AnnotatedDeploymentModel;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Component;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.ComponentType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.Relation;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.RelationType;
import ust.tad.modelsservice.technologyagnosticdeploymentmodel.exceptions.EntityNotFoundException;

@Service
public class TypeCompletenessService {
    
    @Autowired
    private TechnologyAgnosticDeploymentModelService service;
    
    public double calculateTypeCompletenessVal1(UUID transformationProcessId) throws EntityNotFoundException {
        AnnotatedDeploymentModel tadm = 
            service.getTechnologyAgnosticDeploymentModelByTransformationProcessId(transformationProcessId);
        double sumOfActualPropertiesAndOperations = 0;
        double sumOfRequiredPropertiesAndOperations = 0;
        for (Component component : tadm.getComponents()) {
            sumOfActualPropertiesAndOperations += component.getProperties().size() 
                + component.getOperations().size();
            sumOfRequiredPropertiesAndOperations += calculateSumOfRequiredPropertiesAndOperationsForComponentType(component.getType());
        }
        for (Relation relation : tadm.getRelations()) {
            sumOfActualPropertiesAndOperations += relation.getProperties().size() 
                + relation.getOperations().size();
            sumOfRequiredPropertiesAndOperations += calculateSumOfRequiredPropertiesAndOperationsForRelationType(relation.getType());
        }
        if (sumOfRequiredPropertiesAndOperations == 0) {
            return 0D;
        }
        return sumOfActualPropertiesAndOperations / sumOfRequiredPropertiesAndOperations;
    }

    public double calculateTypeCompletenessVal2(UUID transformationProcessId) throws EntityNotFoundException {
        AnnotatedDeploymentModel tadm = 
            service.getTechnologyAgnosticDeploymentModelByTransformationProcessId(transformationProcessId);

        double sumOfTypeCompleteEntities = (double)tadm.getComponents().stream().filter(this::isComponentTypeComplete).count()
            + tadm.getRelations().stream().filter(this::isRelationTypeComplete).count();
        double sumOfEntities = (double)tadm.getComponents().size() + tadm.getRelations().size();
        if (sumOfEntities == 0) {
            return 0D;
        }
        return sumOfTypeCompleteEntities / sumOfEntities;
    }

    private int calculateSumOfRequiredPropertiesAndOperationsForComponentType(ComponentType componentType) {
        int result = 0;
        result += componentType.getProperties().size() + componentType.getOperations().size();
        if (componentType.getParentType() != null) {
            result += calculateSumOfRequiredPropertiesAndOperationsForComponentType(componentType.getParentType());
        }
        return result;
    }

    private int calculateSumOfRequiredPropertiesAndOperationsForRelationType(RelationType relationType) {
        int result = 0;
        result += relationType.getProperties().size() + relationType.getOperations().size();
        if (relationType.getParentType() != null) {
            result += calculateSumOfRequiredPropertiesAndOperationsForRelationType(relationType.getParentType());
        }
        return result;
    }    

    private Boolean isComponentTypeComplete(Component component) {
        int sumOfActualPropertiesAndOperations = component.getProperties().size() 
            + component.getOperations().size();
        int sumOfRequiredPropertiesAndOperations = calculateSumOfRequiredPropertiesAndOperationsForComponentType(component.getType());
        return sumOfActualPropertiesAndOperations == sumOfRequiredPropertiesAndOperations;
    }

    private Boolean isRelationTypeComplete(Relation relation) {
        int sumOfActualPropertiesAndOperations = relation.getProperties().size() 
            + relation.getOperations().size();
        int sumOfRequiredPropertiesAndOperations = calculateSumOfRequiredPropertiesAndOperationsForRelationType(relation.getType());
        return sumOfActualPropertiesAndOperations == sumOfRequiredPropertiesAndOperations;
    }
}
