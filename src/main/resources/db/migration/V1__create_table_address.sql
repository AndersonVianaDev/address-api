CREATE TABLE tb_addresses (
    id uuid PRIMARY KEY,
    zip_code VARCHAR(8),
    locality VARCHAR(60),
    uf VARCHAR(2),
    neighborhood VARCHAR(60),
    complement VARCHAR(100),
    number VARCHAR(5),
    created_at timestamp(6) DEFAULT CURRENT_TIMESTAMP(6),
    updated_at timestamp(6) DEFAULT CURRENT_TIMESTAMP(6)
)