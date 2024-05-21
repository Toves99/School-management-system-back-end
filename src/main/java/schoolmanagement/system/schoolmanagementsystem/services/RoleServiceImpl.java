package schoolmanagement.system.schoolmanagementsystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Role;
import schoolmanagement.system.schoolmanagementsystem.dao.data.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleServices{
    private final RoleRepository roleRepository;
    @Override
    public Role save(Role userRole) {
        return roleRepository.save(userRole);
    }
}
