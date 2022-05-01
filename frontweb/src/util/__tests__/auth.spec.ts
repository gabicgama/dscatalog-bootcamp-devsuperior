import { hasAnyRole } from '../auth';
import * as TokenModule from '../token';

describe('hasAnyRole tests', () => {
  test('should return true when empty list', () => {
    const result = hasAnyRole([]);
    expect(result).toEqual(true);
  });

  test('should return true when user has given role', () => {
    jest.spyOn(TokenModule, 'getTokenData').mockReturnValue({
      exp: 0,
      user_name: '',
      authorities: ['ROLE_ADMIN', 'ROLE_OPERATOR'],
    });
    const result = hasAnyRole(['ROLE_ADMIN']);
    expect(result).toEqual(true);
  });
});
