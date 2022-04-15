package ust.tad.modelsservice.technologyagnosticdeploymentmodel.yamlserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import ust.tad.modelsservice.technologyagnosticdeploymentmodel.entities.*;

public class YamlObjectMapper {

    public static ObjectMapper createYamlObjectMapper() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR));
        SimpleModule module = new SimpleModule();
        module.addSerializer(Artifact.class, new ArtifactSerializer());
        module.addSerializer(Component.class, new ComponentSerializer());
        module.addSerializer(TechnologyAgnosticDeploymentModel.class, new DeploymentModelSerializer());
        module.addSerializer(Operation.class, new OperationSerializer());
        module.addSerializer(Property.class, new PropertySerializer());
        module.addSerializer(Relation.class, new RelationSerializer());
        module.addSerializer(ComponentType.class, new ComponentTypeSerializer());
        module.addSerializer(RelationType.class, new RelationTypeSerializer());
        mapper.registerModule(module);
        return mapper;
    }
    
}
