package net.th1232.action.authorization;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;

import net.th1232.action.AbstractAction;
import net.th1232.model.base.Role;
import net.th1232.model.base.User;

@Scope(ScopeType.APPLICATION)
@Name("authorization")
public class Authorization extends AbstractAction{
	public static Authorization instance(){
		return (Authorization) Contexts.getApplicationContext().get("authenticator");		
	}
	private Map<String, List<String>> rolePermissionMap = new HashMap<String, List<String>>();
	
	@Create
	public void init(){
		
	}
	public Set<String> getPermissionsForUser(User user){
		Set<String> permissions = new HashSet<String>();
		List<Role> roleList = user.getRoles();
		for (Role role : roleList) {
			List<String> rolePermission = rolePermissionMap.get(role.getName());
			for (String perm : rolePermission) {
				permissions.add(perm);
			}
		}
		return permissions;
	}
}
