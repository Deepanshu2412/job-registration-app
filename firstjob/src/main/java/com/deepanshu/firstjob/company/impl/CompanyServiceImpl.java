package com.deepanshu.firstjob.company.impl;

import com.deepanshu.firstjob.company.Company;
import com.deepanshu.firstjob.company.CompanyRepository;
import com.deepanshu.firstjob.company.CompanyService;
import com.deepanshu.firstjob.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company,Long id) {
        Optional<Company> companyOptional=companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company CompanyToUpdate = companyOptional.get();
            CompanyToUpdate.setDescription(company.getDescription());
            CompanyToUpdate.setName(company.getName());
            CompanyToUpdate.setJobs(company.getJobs());
            companyRepository.save(CompanyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
