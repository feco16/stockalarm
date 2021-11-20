package stockalarm.service;

import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import stockalarm.to.StockUserRegistrationDTO;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class KeycloakAdminClientService {

    @Value("${stockalarm.keycloak.username}")
    private String username;

    @Value("${stockalarm.keycloak.password}")
    private String password;

    private static Keycloak keycloak;
    private final KeycloakSpringBootProperties keycloakSpringBootProperties;

    public int addUser(final StockUserRegistrationDTO user) {
        final UsersResource usersResource = getInstance().realm(keycloakSpringBootProperties.getRealm()).users();
        final CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        final UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getEmail());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstName());
        kcUser.setLastName(user.getLastName());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        return usersResource.create(kcUser).getStatusInfo().getStatusCode();

    }

    private Keycloak getInstance() {
        if (keycloak == null) {

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(keycloakSpringBootProperties.getAuthServerUrl())
                    .realm(keycloakSpringBootProperties.getRealm())
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(username)
                    .password(password)
                    .clientId(keycloakSpringBootProperties.getResource())
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build()
                    )
                    .build();
        }
        return keycloak;
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        final CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
