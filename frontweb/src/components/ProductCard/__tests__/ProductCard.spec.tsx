import { render, screen } from '@testing-library/react';
import ProductCard from '..';

test('should render ProductCard', () => {
  const text = 'The Lord of the Rings';
  render(
    <ProductCard
      product={{
        id: 1,
        name: text,
        imgUrl: '',
        price: 90.5,
        description: '',
        date: '',
        categories: [],
      }}
    />
  );
  expect(screen.getByText(text)).toBeInTheDocument();
  expect(screen.getByAltText(text)).toBeInTheDocument();
  expect(screen.getByText('R$')).toBeInTheDocument();
  expect(screen.getByText('90,50')).toBeInTheDocument();
});
