import './styles.css';
import { ReactComponent as SearchIcon } from 'assets/images/search-icon.svg';
import { Category } from 'types/category';
import { Controller, useForm } from 'react-hook-form';
import { useEffect, useState } from 'react';
import { requestBackend } from 'util/requests';
import Select from 'react-select';

type ProductFilterData = {
  name: string;
  category: Category;
};

const ProductFilter = () => {
  const [selectCategories, setSelectCategories] = useState<Category[]>([]);

  const { register, handleSubmit, control } = useForm<ProductFilterData>();

  const onSubmit = (formData: ProductFilterData) => {
    console.log('enviou', formData);
  };

  useEffect(() => {
    requestBackend({ url: '/categories' }).then((response) => {
      setSelectCategories(response.data.content);
    });
  }, []);

  return (
    <div className="base-card product-filter-container">
      <form onSubmit={handleSubmit(onSubmit)} className="product-filter-form">
        <div className="product-filter-name-container">
          <input
            {...register('name')}
            type="text"
            className="form-control"
            placeholder="Nome do produto"
            name="name"
          />
          <button>
            <SearchIcon />
          </button>
        </div>
        <div className="product-filter-bottom-container">
          <div className="product-filter-category-container">
            <Controller
              name="category"
              control={control}
              render={({ field }) => (
                <Select
                  {...field}
                  options={selectCategories}
                  classNamePrefix="product-crud-select"
                  isClearable
                  getOptionLabel={(category: Category) => category.name}
                  getOptionValue={(category: Category) => String(category.id)}
                />
              )}
            />
          </div>
          <button className="btn btn-outline-secondary">Limpar</button>
        </div>
      </form>
    </div>
  );
};

export default ProductFilter;
