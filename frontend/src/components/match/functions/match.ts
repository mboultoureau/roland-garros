export function useMatchCard() {
  const reduceNamePlayer = (firstname: string, lastname: string) => {
    const firstLetter = firstname.charAt(0).toUpperCase();
    return `${firstLetter}.${lastname}`;
  };

  return {
    reduceNamePlayer,
  };
}
