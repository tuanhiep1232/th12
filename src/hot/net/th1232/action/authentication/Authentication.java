package net.th1232.action.authentication;

import java.util.Set;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Identity;

import net.th1232.action.AbstractAction;
import net.th1232.action.authorization.Authorization;
import net.th1232.model.base.User;

@Name("authenticator")
@Scope(ScopeType.SESSION)
public class Authentication extends AbstractAction{
    @In protected Identity identity;
	public boolean authenticate(){		
		User u = (User) entityManager.createQuery("select e from User e where e.username = ? and e.password =?").
			setParameter(0, credentials.getUsername()).
			setParameter(1, credentials.getPassword())
			.getSingleResult();
		if(u==null) return false;
		Set<String> userPermissions = authorization.getPermissionsForUser(u);
		for (String perm : userPermissions) {
			identity.addRole(perm);
		}
		return true;
	}
}
