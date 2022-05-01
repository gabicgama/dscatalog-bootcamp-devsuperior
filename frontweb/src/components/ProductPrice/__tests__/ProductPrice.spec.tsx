import { render, screen } from '@testing-library/react';
import ProductPrice from '..';

test('should render ProductPrice', () => {
  render(<ProductPrice price={100} />);
  expect(screen.getByText('R$')).toBeInTheDocument();
  expect(screen.getByText('100,00')).toBeInTheDocument();
});
